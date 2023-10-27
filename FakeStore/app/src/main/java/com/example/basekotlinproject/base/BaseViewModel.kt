package com.example.basekotlinproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel(open val app: Application) : AndroidViewModel(app) {

}