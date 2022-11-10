

open class BaseFragment : Fragment() {

    protected val mActivity by lazy { this }
    private var progressDialog: Dialog? = null

    protected val mUserViewModel by lazy {
        ViewModelProviders.of(mActivity).get(UserViewModel::class.java)
    }

    fun startActivityOnTop(cls: Class<*>, finishCurrent: Boolean) {
        val intent = Intent(activity, cls)
        if (finishCurrent) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        startActivity(intent)
        if (finishCurrent) {
            activity?.finish()
        }
        activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun startActivityOnTop(cls: Class<*>) {
        startActivityOnTop(cls, false)
    }

    fun startActivityOnTop(intent: Intent, finishCurrent: Boolean) {
        startActivity(intent)
        if (finishCurrent) {
            activity?.finish()
        }
        activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun startActivityOnTop(intent: Intent) {
        startActivityOnTop(intent, false)
    }

    @Synchronized
    open fun dismissProgressDialog() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }

    @Synchronized
    open fun showProgressDialog() {
        showProgressDialog(getString(R.string.loading))
    }

    @Synchronized
    open fun showToast(value: String) {
        Toast.makeText(activity, value, Toast.LENGTH_SHORT).show()
    }

    @Synchronized
    open fun showProgressDialog(strLoader: String) {
        if (!(activity?.isFinishing)!!) {
            progressDialog?.let {
                if (!it.isShowing)
                    it.show()
            } ?: kotlin.run {
                progressDialog = Utils.getProgressDialog(activity, false, strLoader)
                progressDialog?.show()
            }
        }
    }

}
