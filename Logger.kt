

import android.util.Log

object Logger {

    private var isLogEnabled = true
    private var logLevel = Level.VERBOSE

    private fun getClassName(`object`: Any): String? {
        var mClassName = `object`.javaClass.name
        var mFirstPosition = mClassName.lastIndexOf(".") + 1
        if (mFirstPosition < 0) {
            mFirstPosition = 0
        }
        mClassName = mClassName.substring(mFirstPosition)
        mFirstPosition = mClassName.lastIndexOf("$")
        if (mFirstPosition > 0) {
            mClassName = mClassName.substring(0, mFirstPosition)
        }
        return mClassName
    }

    private enum class Level {
        ERROR, WARN, INFO, DEBUG, VERBOSE
    }

    fun d(msg: String?) {
        d("Parking", msg)
    }

    fun d(any: Any, msg: String?) {
        if (isLogEnabled && logLevel.ordinal >= Level.DEBUG.ordinal) {
            msg?.let { Log.d(if (any is String) any else getClassName(any), it) }
        }
    }

    fun i(any: Any, msg: String?) {
        if (isLogEnabled && logLevel.ordinal >= Level.INFO.ordinal) {
            msg?.let { Log.i(if (any is String) any else getClassName(any), it) }
        }
    }

    fun e(any: Any, msg: String?) {
        if (isLogEnabled && logLevel.ordinal >= Level.ERROR.ordinal) {
            msg?.let { Log.e(if (any is String) any else getClassName(any), it) }
        }
    }

    fun e(any: Any, msg: String?, t: Throwable) {
        if (isLogEnabled && logLevel.ordinal >= Level.ERROR.ordinal) {
            Log.e(if (any is String) any else getClassName(any), msg, t)
        }
    }
}
