package com.example.basekotlinproject.utils.extention

import android.os.Build
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*


fun String.getStringDate(
    initialFormat: String,
    requiredFormat: String,
    locale: Locale = Locale.getDefault()
): String {
    return this.toDate(initialFormat, locale).toString(requiredFormat)
}

fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.parse(this) ?: Date()
    } catch (ex: Exception) {
        Date()
    }
}

fun String.toTimeStamp(format: String, locale: Locale = Locale.getDefault()): Long? {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        (formatter.parse(this) ?: return 0L).time / 1000
    } catch (ex: Exception) {
        null
    }
}

fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun Long.toTimeString(format: String, locale: Locale = Locale.getDefault()): String {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        return formatter.format(this * 1000)
    } catch (ex: Exception) {
        ""
    }
}

fun Long.toTimeDate(format: String, locale: Locale = Locale.getDefault()): Date {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        return formatter.format(this * 1000).toDate(format)
    } catch (ex: Exception) {
        Date()
    }
}


fun Long.toTimeStampGMT(format: String, locale: Locale = Locale.getDefault()): String {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatter.timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())
        }
        return formatter.format(this * 1000)
    } catch (ex: Exception) {
        ""
    }
}

fun isDateInCurrentWeek(date: Date?): Boolean {
    val currentCalendar = Calendar.getInstance()
    val week = currentCalendar[Calendar.WEEK_OF_YEAR]
    val year = currentCalendar[Calendar.YEAR]
    val targetCalendar = Calendar.getInstance()
    if (date != null) {
        targetCalendar.time = date
    }
    val targetWeek = targetCalendar[Calendar.WEEK_OF_YEAR]
    val targetYear = targetCalendar[Calendar.YEAR]
    return week == targetWeek && year == targetYear
}

fun isToday(whenInMillis: Long): Boolean {
    return DateUtils.isToday(whenInMillis)
}

fun Long.toTimeStampUTC(format: String, locale: Locale = Locale.getDefault()): String {
    return try {
        val formatter = SimpleDateFormat(format, locale)
        formatter.timeZone = TimeZone.getDefault()
        return formatter.format(this * 1000)
    } catch (ex: Exception) {
        ""
    }
}

