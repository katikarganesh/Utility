package `in`.shree.marathiaaratisangrah.views

import android.content.Context
import android.graphics.Typeface
import java.util.*


object TypefaceLoader {

    private val TAG = javaClass.name

    private val cache = Hashtable<String, Typeface>()

    operator fun get(context: Context, assetPath: String): Typeface? {
        synchronized(cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    cache[assetPath] = Typeface.createFromAsset(context.assets, assetPath)
                } catch (e: Exception) {
                    return get(context, assetPath)
                }
            }
            return cache[assetPath]
        }
    }
}