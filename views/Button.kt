package `in`.shree.marathiaaratisangrah.views

import `in`.shree.marathiaaratisangrah.R
import `in`.shree.marathiaaratisangrah.utils.AppConstants
import `in`.shree.marathiaaratisangrah.utils.Utils
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton


class Button : AppCompatButton {

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
            val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.Button)
            val index = styledAttrs.getInt(R.styleable.Button_customTypeface, 1)
            styledAttrs.recycle()
            val typeface = TypefaceLoader[context, Utils.getFontName(index)]
            setTypeface(typeface)
        }
    }
}