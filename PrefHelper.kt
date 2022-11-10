

class PrefHelper {
    private var prefs: SharedPreferences

    private constructor(context: Context) {
        prefs = context.applicationContext.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }

    private constructor(context: Context, sharePreferencesName: String) {
        prefs = context.applicationContext.getSharedPreferences(
            sharePreferencesName,
            Context.MODE_PRIVATE
        )
    }

    companion object {
        private var instance: PrefHelper? = null
        private fun getInstance(): PrefHelper? {
            requireNotNull(instance) { "PrefHelper must be call initHelper on Application before using." }
            return instance
        }

        fun initHelper(context: Context): PrefHelper? {
            if (instance == null) instance = PrefHelper(context)
            return instance
        }

        fun initHelper(context: Context, sharePreferencesName: String): PrefHelper? {
            if (instance == null) instance = PrefHelper(context, sharePreferencesName)
            return instance
        }

        fun setVal(KEY: String?, value: Boolean) {
            getInstance()!!.prefs.edit().putBoolean(KEY, value).apply()
        }

        fun setVal(KEY: String?, value: String?) {
            getInstance()!!.prefs.edit().putString(KEY, value).apply()
        }

        fun setVal(KEY: String?, value: Any?) {
            getInstance()!!.prefs.edit().putString(KEY, Gson().toJson(value)).apply()
        }

        fun setVal(KEY: String?, value: Int) {
            getInstance()!!.prefs.edit().putInt(KEY, value).apply()
        }

        fun setVal(KEY: String?, value: Long) {
            getInstance()!!.prefs.edit().putLong(KEY, value).apply()
        }

        fun setVal(KEY: String?, value: Float) {
            getInstance()!!.prefs.edit().putFloat(KEY, value).apply()
        }

        fun setVal(KEY: String?, value: Double) {
            setVal(KEY, value.toString())
        }

        fun <T> setVal(KEY: String?, list: List<T>?) {
            setVal(KEY, Gson().toJson(list))
        }

        fun <K, V> setVal(KEY: String?, map: Map<K, V>?) {
            setVal(KEY, Gson().toJson(map))
        }

        fun <T> setVal(KEY: String?, array: Array<T>) {
            val jArray = JSONArray()
            for (t in array) {
                jArray.put(t)
            }
            getInstance()!!.prefs.edit().putString(KEY, Gson().toJson(jArray)).apply()
        }

        fun <T> getArrayVal(KEY: String?): Array<T>? {
            val results: Array<T>? = null
            try {
                val jArray = JSONArray(getInstance()!!.prefs.getString(KEY, ""))
                for (i in 0 until jArray.length()) {
                    results!![i] = jArray[i] as T
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return results
        }

        fun <T> getListVal(KEY: String?): List<T>? {
            var objects: List<T>? = null
            try {
                val obj = getInstance()!!.prefs.getString(KEY, "")
                objects = Gson().fromJson(obj, object : TypeToken<List<T>?>() {}.type)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return objects
        }

        fun <K, V> getMapVal(KEY: String?): Map<K, V>? {
            var objects: Map<K, V>? = null
            try {
                val obj = getInstance()!!.prefs.getString(KEY, "")
                objects = Gson().fromJson(obj, object : TypeToken<Map<K, V>?>() {}.type)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return objects
        }

        fun getBooleanVal(KEY: String?, defvalue: Boolean): Boolean {
            return getInstance()!!.prefs.getBoolean(KEY, defvalue)
        }

        fun getBooleanVal(KEY: String?): Boolean {
            return getInstance()!!.prefs.getBoolean(KEY, false)
        }

        fun getStringVal(KEY: String?, defvalue: String?): String? {
            return getInstance()!!.prefs.getString(KEY, defvalue)
        }

        fun getStringVal(KEY: String?): String? {
            return getInstance()!!.prefs.getString(KEY, null)
        }

        fun <T> getObjectVal(KEY: String?, mModelClass: Class<T>?): T {
            var `object`: Any? = null
            try {
                `object` = Gson().fromJson(getInstance()!!.prefs.getString(KEY, ""), mModelClass)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return Primitives.wrap(mModelClass).cast(`object`)
        }

        fun getIntVal(KEY: String?, defVal: Int): Int {
            return getInstance()!!.prefs.getInt(KEY, defVal)
        }

        fun getIntVal(KEY: String?): Int {
            return getInstance()!!.prefs.getInt(KEY, 0)
        }

        fun getLongVal(KEY: String?, defVal: Long): Long {
            return getInstance()!!.prefs.getLong(KEY, defVal)
        }

        fun getLongVal(KEY: String?): Long {
            return getInstance()!!.prefs.getLong(KEY, 0)
        }

        fun getFloatVal(KEY: String?, defVal: Float): Float {
            return getInstance()!!.prefs.getFloat(KEY, defVal)
        }

        fun getFloatVal(KEY: String?): Float {
            return getInstance()!!.prefs.getFloat(KEY, 0f)
        }

        fun getDoubleVal(KEY: String?, defVal: Double): Double {
            return getStringVal(KEY, defVal.toString())!!.toDouble()
        }

        fun getDoubleVal(KEY: String?): Double {
            return getStringVal(KEY, 0.toString())!!.toDouble()
        }

        val all: Map<String, *>
            get() = getInstance()!!.prefs.all

        fun removeKey(KEY: String?) {
            getInstance()!!.prefs.edit().remove(KEY).apply()
        }

        fun removeAllKeys() {
            getInstance()!!.prefs.edit().clear().apply()
        }

        fun contain(KEY: String?): Boolean {
            return getInstance()!!.prefs.contains(KEY)
        }

        fun registerChangeListener(listener: OnSharedPreferenceChangeListener?) {
            getInstance()!!.prefs.registerOnSharedPreferenceChangeListener(listener)
        }

        fun unregisterChangeListener(listener: OnSharedPreferenceChangeListener?) {
            getInstance()!!.prefs.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }
}
