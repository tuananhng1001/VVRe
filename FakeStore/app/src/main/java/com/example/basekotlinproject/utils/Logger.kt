package com.example.basekotlinproject.utils

import android.util.Log

object Logger {
    private const val TAG: String = "Logger"
    private var isDebug = true
    private const val isLog = true

    fun v(msg: String?) {
        verbose(msg)
    }

    fun v(tag: String?, msg: String?) {
        if (isDebug) verbose(tag, msg)
    }

    fun d(msg: String?) {
        debug(msg)
    }

    fun d(tag: String?, msg: String?) {
        if (isDebug) debug(tag, msg)
    }

    fun i(msg: String?) {
        info(msg)
    }

    fun i(tag: String?, msg: String?) {
        if (isDebug) info(tag, msg)
    }

    fun w(msg: String?) {
        warning(msg)
    }

    fun w(tag: String?, msg: String?) {
        if (isDebug) warning(tag, msg)
    }

    fun e(msg: String?) {
        error(msg)
    }

    fun e(tag: String?, msg: String?) {
        if (isDebug) error(tag, msg)
    }

    fun longLog(tag: String?, veryLongString: String) {
        val maxLogSize = 2000
        for (i in 0..veryLongString.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > veryLongString.length) veryLongString.length else end
            d(tag, veryLongString.substring(start, end))
        }
    }

    fun longLog(veryLongString: String) {
        val maxLogSize = 4000
        for (i in 0..veryLongString.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > veryLongString.length) veryLongString.length else end
            d(veryLongString.substring(start, end))
        }
    }

    fun verbose(msg: String?) {
        verbose(TAG, msg)
    }

    fun verbose(tag: String?, msg: String?) {
        print(Level.verbose, tag, msg)
    }

    fun info(msg: String?) {
        info(TAG, msg)
    }

    fun info(tag: String?, msg: String?) {
        print(Level.info, tag, msg)
    }

    fun debug(msg: String?) {
        debug(TAG, msg)
    }

    fun debug(tag: String?, msg: String?) {
        print(Level.debug, tag, msg)
    }

    fun warning(msg: String?) {
        warning(TAG, msg)
    }

    fun warning(tag: String?, msg: String?) {
        print(Level.warning, tag, msg)
    }

    fun error(msg: String?) {
        error(TAG, msg)
    }

    fun error(tag: String?, msg: String?) {
        print(Level.error, tag, msg)
    }


    private fun print(level: Level, tag: String?, msg: String?) {
        val logLevel = level.ordinal
        val currentLogLevel = level.ordinal
        if (currentLogLevel < logLevel) {
            // Log.d(tag, "log level too low");
        } else if (isLog) {
            // create data
            val element = Throwable().stackTrace[3]
            val file = element.className
            val filename = file.replace(
                java.lang.String.format(
                    Setting.Format.oldString,
                  //  BuildConfig.LIBRARY_PACKAGE_NAME
                ), Setting.Constant.newString
            )
            val method = element.methodName
            val line = element.lineNumber
            val dataFile =
                String.format(Setting.Format.dataFileLog, level.name, filename, method, line, msg)
            val dataConsole =
                String.format(Setting.Format.dataConsoleLog, filename, method, line, msg)
            when (level) {
                Level.error -> Log.e(tag, dataConsole)
                Level.warning -> Log.w(tag, dataConsole)
                Level.debug -> Log.d(tag, dataConsole)
                Level.info -> Log.i(tag, dataConsole)
                else -> Log.v(tag, dataConsole)
            }
        } else {
            // Log.d(tag, "configure log fail");
        }
    }

    fun e(msg: Exception) {
        error(TAG, msg.localizedMessage)
    }

    enum class Level {
        verbose, debug, info, warning, error
    }

    private interface Setting {
        interface Format {
            companion object {
                const val date = "yyyyMMdd"
                const val fileName = "%s%s"
                const val dataFileLog = "[%s][%s_%s(%s)] : %s \n"
                const val dataConsoleLog = "[%s_%s(%s)] : %s \n"
                const val oldString = "%s."
            }
        }

        interface Config {
            companion object {
                const val configTime: Long = 6
            }
        }

        interface Constant {
            companion object {
                const val newString = ""
                const val patternFormat = "0.##"
            }
        }
    }
}
