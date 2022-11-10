package com.instazen.app.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.instazen.app.R

class NoInternetDialog : BaseDialogFragment() {

    companion object {

        fun getInstance(callback: OnDismissListener?): NoInternetDialog {
            val fragment = NoInternetDialog()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    var btnTryAgain: Button? = null

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.no_internet_layout, container, false)
        btnTryAgain = view.findViewById(R.id.btnTryAgain)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        dialog?.window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.red)
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.attributes!!.windowAnimations = R.style.DialogAnimation
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTryAgain?.setOnClickListener {
            dismiss()
        }
    }
}