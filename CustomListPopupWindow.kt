

class CustomListPopupWindow(context: Context?) : ListPopupWindow(context!!) {
    private var isShowing = false

    init {
        width = Utils.getDpToPixel(200)
        isModal = true
    }

    override fun isShowing(): Boolean {
        super.isShowing()
        return isShowing
    }

    override fun show() {
        isShowing = true
        super.show()
    }

    override fun dismiss() {
        super.dismiss()
        isShowing = false
    }
}
