package com.instazen.app.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.instazen.app.apis.isInternetConnected


open class BaseDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
      /*  dialog.window?.run {
            requestFeature(Window.FEATURE_NO_TITLE)
            setGravity(Gravity.CENTER)
            setStyle(STYLE_NORMAL, R.style.CustomDialogTheme)
            //setBackgroundDrawableResource(android.R.color.transparent)
        }*/
        return dialog
    }

    protected fun isActiveInternet(): Boolean {
        return if (!isInternetConnected()) {
            showOffNetworkDialog()
            false
        } else {
            true
        }
    }

    protected fun showOffNetworkDialog() {
        NoInternetDialog.getInstance(object : OnDismissListener {
            override fun onDismiss() {
            }
        }).show(childFragmentManager, null)
    }
}