

open class BaseActivity : AppCompatActivity() {

    protected val mActivity by lazy { this }

    /*View Models*/
    protected val mUserViewModel by lazy {
        ViewModelProviders.of(mActivity).get(UserViewModel::class.java)
    }

    private var progressDialog: Dialog? = null

    fun finishScreen() {
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun startActivityOnTop(cls: Class<*>, finishCurrent: Boolean) {
        val intent = Intent(mActivity, cls)
        if (finishCurrent) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        startActivity(intent)
        if (finishCurrent) {
            finish()
        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun startActivityOnTop(cls: Class<*>) {
        startActivityOnTop(cls, false)
    }

    fun startActivityOnTop(intent: Intent, finishCurrent: Boolean) {
        startActivity(intent)
        if (finishCurrent) {
            finish()
        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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
    open fun showProgressDialog(loaderMsg: String) {
        if (!isFinishing) {
            progressDialog?.let {
                if (!it.isShowing)
                    it.show()
            } ?: kotlin.run {
                progressDialog = Utils.getProgressDialog(mActivity, false, loaderMsg)
                progressDialog?.show()
            }
        }
    }

    protected fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    protected fun showToast(message: String) {
        if (message.isNotEmpty()) {
            Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    protected fun somethingWentWrong() {
        Toast.makeText(mActivity, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT)
            .show()
    }

    override fun onBackPressed() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    protected fun isActiveInternetConnection(): Boolean{
        return if(isInternetConnected()){
            true
        }else{
            showToast(R.string.no_internet_connection)
            false
        }
    }


}
