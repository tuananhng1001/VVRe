package com.example.basekotlinproject.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.basekotlinproject.BuildConfig
import com.example.basekotlinproject.data.remote.BaseService
import com.example.basekotlinproject.utils.SharePreference
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun providesFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            // increase the number of fetches available per hour during development.
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else 600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        // Set default Remote Config parameter values. An app uses the in-app default values, and
        // when you need to adjust those defaults, you set an updated value for only the values you
        // want to change in the Firebase console.
        remoteConfig.setDefaultsAsync(
            mapOf(
                "android_base_url" to BuildConfig.API_DOMAIN,
                "android_version" to BuildConfig.VERSION_NAME.split("-")[0],
            )
        )
        return remoteConfig
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        remoteConfig: FirebaseRemoteConfig,
        @ApplicationContext context: Context,
        sharePreference: SharePreference
    ): Retrofit {
        val logging = HttpLoggingInterceptor()
        // Create the Collector
        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
            )
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()

        logging.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        val utc = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        val httpClient: OkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request()
                val newRequest = request.newBuilder()
                    .addHeader(
                        "Authorization",
                        "Bearer ${sharePreference.get<String>(SharePreference.ACCESS_TOKEN_PREF.first) ?: ""}"
                    )
                    .build()

                chain.proceed(newRequest)
            }
            .cache(null)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_DOMAIN)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthService(retrofit: Retrofit): BaseService =
        retrofit.create(BaseService::class.java)
}