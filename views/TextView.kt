package `in`.shree.marathiaaratisangrah.views

import `in`.shree.marathiaaratisangrah.R
import `in`.shree.marathiaaratisangrah.utils.AppConstants
import `in`.shree.marathiaaratisangrah.utils.Utils
import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat


class TextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init() {
        if (!isInEditMode) {
            val typeface = TypefaceLoader[context, AppConstants.FONTS_DIR + AppConstants.DEFAULT_FONT_NAME]
            setTypeface(typeface)
        }
    }

    private fun init(context: Context, attrs: AttributeSet) {
        if (!isInEditMode) {
            val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TextView)
            val index = styledAttrs.getInt(R.styleable.TextView_customTypeface, 1)
            styledAttrs.recycle()
            val typeface = TypefaceLoader[context, Utils.getFontName(index)]
            setTypeface(typeface)
        }
    }

    /**
     * Change the color of a part of the text contained in this textView
     *
     * @param subStringToColorize has to already be set in the textView's text
     * @param colorResId
     */
    fun colorize(subStringToColorize: String, @ColorRes colorResId: Int) {

        val spannable: Spannable = SpannableString(text)

        val startIndex = text.indexOf(subStringToColorize, startIndex = 0, ignoreCase = false)
        val endIndex = startIndex + subStringToColorize.length

        val color: Int = ContextCompat.getColor(context, colorResId)

        if (startIndex != -1) {
            spannable.setSpan(
                ForegroundColorSpan(color),
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setText(spannable, BufferType.SPANNABLE)
        }
    }
}
