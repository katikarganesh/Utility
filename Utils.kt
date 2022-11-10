


object Utils {

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.getResources()
            .getDisplayMetrics().densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.getResources()
            .getDisplayMetrics().densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun getTwoPlaceIntegerValue(value: String): String {
        if (value.length == 1) {
            return "0$value"
        }
        return value
    }

    fun validateStartEndDate(startDate: String, endDate: String): Boolean {
        val from = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val to = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val cmp = from.compareTo(to)
        return when {
            cmp > 0 -> {
                //Start date is after End Date
                false
            }
            cmp < 0 -> {
                //Start date is before End Date
                true
            }
            else -> {
                //print("Both dates are equal")
                false
            }
        }
    }

    /**
     * Expected format is yyyy-MM-dd
     */
    fun getCalendarInstance(dateValue: String): Calendar {
        return try {
            val sdf = SimpleDateFormat(AppConstants.DATE_FORMAT2, Locale.ENGLISH)
            val date = sdf.parse(dateValue)
            val cal = Calendar.getInstance()
            cal.time = date
            cal
        } catch (e: Exception) {
            Calendar.getInstance()
        }
    }

    fun getDateDiff(startDate: String, endDate: String): String {
        return try {
            val from = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val to = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val period = Period.between(from, to)
            var mPeriod = ""

            mPeriod = if (period.years > 0) {
                //Year show
                if (period.months > 0) {
                    "${period.years} Years, ${period.months} Months"
                } else {
                    if (period.months == 0 && period.days > 0) {
                        "${period.years} Years, ${period.days} Days"
                    } else {
                        "${period.months} Months, ${period.days} Days"
                    }
                }
            } else {
                //Month, Days show
                if (period.months > 0) {
                    //show month and days
                    if (period.days > 0) {
                        "${period.months} Months, ${period.days} Days"
                    } else {
                        "${period.days} Days"
                    }
                } else {
                    "${period.days} Days"
                }
            }
            /* mPeriod = if (period.years > 0 && period.months == 0) {
                 "${period.years} Years, ${period.days} Days"
             } else if (period.years > 0 && period.months > 0) {
                 "${period.years} Years, ${period.months} Months"
             } else if (period.years == 0 && period.months > 0) {
                 "${period.months} Months, ${period.days} Days"
             } else if (period.years == 0 && period.months == 0) {
                 "${period.days} Days"
             } else {
                 "-"
             }*/
            /* mPeriod = if (period.years > 0 && period.months > 0) {
                 "${period.years} Years, ${period.months} Months"
             } else if (period.years == 0 && period.months > 0) {
                 "${period.months} Months"
             } else {
                 "${period.days} Days"
             }*/
            mPeriod

        } catch (e: Exception) {
            ""
        }
    }

    fun getDateDiffInDays(startDate: String, endDate: String): String {
        return try {
            val from = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val to = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val period = Period.between(from, to)
            val mPeriod = if (period.days <= 0) {
                "1 Day"
            } else {
                "${period.days + 1} Days"
            }
            mPeriod
        } catch (e: Exception) {
            ""
        }

    }

    fun getBaseUrl(environmentType: String): String {
        if (environmentType == AppConstants.DEV) {
            return AppConstants.DEV_BASE_URL
        } else if (environmentType == AppConstants.STAGING) {
            return AppConstants.STAGING_BASE_URL
        }
        //else it will be prod
        return AppConstants.PROD_BASE_URL
    }

    private const val EMAIL_PATTERN = "^.+@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    /**
     * Validate the email address
     *
     * @param email- That needs to be validated
     * @return true for valid email address
     */
    fun validateEmail(email: String?): Boolean {
        if (!email.isNullOrBlank()) {
            return Pattern.compile(EMAIL_PATTERN).run {
                matcher(email)
            }.matches()
        }
        return false
    }

    fun getHours(durationType: String?): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        val regex = Regex("[^0-9 ]")
        var index: String? = durationType?.let { regex.replace(it, "") }
        if (null == index) {
            index = "7"
        }
        for (i in 0..index.toInt()) {
            list.add(CustomSpinnerItem(i, "$i", "item$i"))
        }
        return list
    }

    fun getMinutes(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "00", "item0"))
        list.add(CustomSpinnerItem(1, "30", "item1"))
        return list
    }

    fun getGender(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Male", "male"))
        list.add(CustomSpinnerItem(1, "Female", "female"))
        list.add(CustomSpinnerItem(2, "Other", "other"))
        return list
    }

    fun getRelation(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Father", "father"))
        list.add(CustomSpinnerItem(1, "Mother", "mother"))
        list.add(CustomSpinnerItem(2, "Brother", "brother"))
        list.add(CustomSpinnerItem(3, "Sister", "sister"))
        list.add(CustomSpinnerItem(4, "Daughter", "daughter"))
        list.add(CustomSpinnerItem(5, "Son", "son"))
        list.add(CustomSpinnerItem(6, "Spouse", "spouse"))
        list.add(CustomSpinnerItem(7, "Father In Law", "father_in_law"))
        list.add(CustomSpinnerItem(8, "Mother In Law", "mother_in_law"))
        return list
    }

    fun getAccountType(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Savings", "savings"))
        list.add(CustomSpinnerItem(1, "Current", "current"))
        return list
    }

    fun getEducationLevel(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Certificate", "CT"))
        list.add(CustomSpinnerItem(1, "Diploma", "DP"))
        list.add(CustomSpinnerItem(2, "Graduate", "GR"))
        list.add(CustomSpinnerItem(3, "Post Graduate", "PG"))
        list.add(CustomSpinnerItem(4, "Doctorate", "DC"))
        list.add(CustomSpinnerItem(5, "Post Diploma", "PD"))
        list.add(CustomSpinnerItem(6, "HSC", "HSC"))
        list.add(CustomSpinnerItem(7, "SSC", "SSC"))
        list.add(CustomSpinnerItem(8, "Other", "OTHER"))
        return list
    }

    fun getDegreeType(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Full Time", "F"))
        list.add(CustomSpinnerItem(1, "Part Time", "P"))
        list.add(CustomSpinnerItem(2, "Distance", "D"))
        return list
    }

    fun getBloodGroup(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "A+", "A+"))
        list.add(CustomSpinnerItem(1, "A-", "A-"))
        list.add(CustomSpinnerItem(2, "B+", "B+"))
        list.add(CustomSpinnerItem(3, "B-", "B-"))
        list.add(CustomSpinnerItem(4, "AB+", "AB+"))
        list.add(CustomSpinnerItem(5, "AB-", "AB-"))
        list.add(CustomSpinnerItem(6, "O+", "O+"))
        list.add(CustomSpinnerItem(7, "O-", "O-"))
        return list
    }

    fun getMaritalStatus(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Married", "Married"))
        list.add(CustomSpinnerItem(1, "Single", "Single"))
        list.add(CustomSpinnerItem(2, "Divorced", "Divorced"))
        list.add(CustomSpinnerItem(3, "Windowed", "Windowed"))
        list.add(CustomSpinnerItem(4, "Engaged", "Engaged"))
        list.add(CustomSpinnerItem(5, "Other", "Other"))
        return list
    }

    fun getSession(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Session 1", "first-half"))
        list.add(CustomSpinnerItem(1, "Session 2", "second-half"))
        return list
    }

    fun getSessionKey(index: Int): String {
        val list = getSession()
        return list[index].key
    }

    fun getSkills(department: String): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        for ((count, value) in HardcodedStrings.getSkills(department).withIndex()) {
            list.add(CustomSpinnerItem(count, value, count.toString()))
        }
        return list
    }

    fun getLevel(): ArrayList<CustomSpinnerItem> {
        val list: ArrayList<CustomSpinnerItem> = ArrayList()
        list.add(CustomSpinnerItem(0, "Select Level", "0"))
        list.add(CustomSpinnerItem(1, "Beginners Level", "B"))
        list.add(CustomSpinnerItem(2, "Intermediate Level", "I"))
        list.add(CustomSpinnerItem(3, "Expert Level", "E"))
        return list
    }

    fun getTagTextByState(state: String): String {
        return when (state.lowercase(Locale.getDefault())) {
            AppConstants.TASK_REOPENED.lowercase(Locale.getDefault()) -> {
                "Reopened"
            }
            AppConstants.TASK_NOT_SUBMITTED.lowercase(Locale.getDefault()) -> {
                "Pending"
            }
            AppConstants.TASK_SUBMITTED.lowercase(Locale.getDefault()) -> {
                "Submitted"
            }
            AppConstants.TASK_APPROVED.lowercase(Locale.getDefault()) -> {
                "Approved"
            }
            AppConstants.TASK_NOT_APPROVED.lowercase(Locale.getDefault()) -> {
                "Rejected"
            }
            AppConstants.TASK_CANCELLED.lowercase(Locale.getDefault()) -> {
                "Cancelled"
            }
            else -> {
                //TASK_ESCALATED
                "Escalated"
            }
        }
    }


    fun getDrawableForTimeSheetState(state: String): Int {
        return when (state.toLowerCase()) {
            AppConstants.TASK_REOPENED.toLowerCase() -> {
                R.drawable.bg_pending//Reopened
            }
            AppConstants.TASK_NOT_SUBMITTED.toLowerCase() -> {
                R.drawable.bg_pending//Pending
            }
            AppConstants.TASK_SUBMITTED.toLowerCase() -> {
                R.drawable.bg_approved//Submitted
            }
            AppConstants.TASK_APPROVED.toLowerCase() -> {
                R.drawable.bg_approved//Approved
            }
            AppConstants.TASK_NOT_APPROVED.toLowerCase() -> {
                R.drawable.bg_rejected_escalated//Rejected
            }
            AppConstants.TASK_CANCELLED.toLowerCase() -> {
                R.drawable.bg_cancelled//Cancelled
            }
            else -> {
                //TASK_ESCALATED//Escalated
                R.drawable.bg_rejected_escalated
            }
        }
    }

    fun getDrawableForLeaveState(state: String): Int {
        return when (state.toLowerCase()) {
            AppConstants.PENDING.toLowerCase() -> {
                R.drawable.bg_pending
            }
            AppConstants.APPROVED.toLowerCase() -> {
                R.drawable.bg_approved
            }
            AppConstants.CANCELLED.toLowerCase() -> {
                R.drawable.bg_cancelled
            }
            else -> {
                R.drawable.bg_rejected_escalated
            }
        }
    }

    fun getDrawableForTimesheet(state: String): Int {
        return when (state.lowercase()) {
            AppConstants.TASK_SUBMITTED.lowercase() -> {
                R.drawable.bg_approved
            }
            else -> {
                R.drawable.bg_pending
            }
        }
    }

    fun getTextColorForLeaveType(state: String): Int {
        return when (state.uppercase(Locale.getDefault())) {
            AppConstants.PENDING.uppercase(Locale.getDefault()) -> {
                R.color.black
            }
            AppConstants.CANCELLED.uppercase(Locale.getDefault()) -> {
                R.color.black
            }
            else -> {
                R.color.white
            }
        }
    }

    fun getBackgroundColorForLeaveType(state: String): Int {
        return when (state.toUpperCase()) {
            AppConstants.PENDING.toUpperCase() -> {
                R.color.yellow
            }
            AppConstants.APPROVED.toUpperCase() -> {
                R.color.green
            }
            AppConstants.CANCELLED.toUpperCase() -> {
                R.color.color_grey
            }
            else -> {
                R.color.red
            }
        }
    }

    fun getStatusFilterList(): ArrayList<PopupMenuItem> {
        val listItem = ArrayList<PopupMenuItem>()
        listItem.add(PopupMenuItem(AppConstants.FILTER_BY_STATUS, R.color.text_color_black, false))
        listItem.add(PopupMenuItem(AppConstants.SHOW_ALL, R.color.text_color_black, false))
        listItem.add(PopupMenuItem(AppConstants.PENDING, R.color.text_color_black, true))
        listItem.add(PopupMenuItem(AppConstants.APPROVED, R.color.text_color_black, false))
        listItem.add(PopupMenuItem(AppConstants.REJECTED, R.color.text_color_black, false))
        listItem.add(PopupMenuItem(AppConstants.ESCALATED, R.color.text_color_black, false))
        listItem.add(PopupMenuItem(AppConstants.CANCELLED, R.color.text_color_black, false))
        return listItem
    }

    fun getPopupMenusOption(): ArrayList<PopupMenuItem> {
        val listItem = ArrayList<PopupMenuItem>()
        listItem.add(
            PopupMenuItem(
                AppConstants.OPTION_LEAVE_OVERVIEW,
                R.color.disabled_color,
                true
            )
        )
        listItem.add(PopupMenuItem(AppConstants.OPTION_HOLIDAYS, R.color.text_color_black, true))
        return listItem
    }

    fun getPopupMenusSorting(): ArrayList<PopupMenuItem> {
        val listItem = ArrayList<PopupMenuItem>()
        listItem.add(PopupMenuItem(AppConstants.SORT_BY_DATE, R.color.disabled_color, false))
        listItem.add(PopupMenuItem(AppConstants.ASCENDING, R.color.text_color_black, true))
        listItem.add(PopupMenuItem(AppConstants.DESCENDING, R.color.dark_grey, false))
        return listItem
    }

    fun getPopupMenusYear(): ArrayList<PopupMenuItem> {
        val listItem = ArrayList<PopupMenuItem>()
        listItem.add(PopupMenuItem(AppConstants.FILTER_BY_YEAR, R.color.disabled_color, false))
        for (i in 0..5) {
            val prevYear: Calendar = Calendar.getInstance()
            prevYear.add(Calendar.YEAR, -i)
            val year = prevYear.get(Calendar.YEAR)
            if ((i == 0)) {
                listItem.add(PopupMenuItem(year.toString(), R.color.text_color_black, true))
            } else {
                listItem.add(PopupMenuItem(year.toString(), R.color.dark_grey, false))
            }
        }
        return listItem
    }

    fun getPopupMenuProfile(): ArrayList<PopupMenuItem> {
        val listItem = ArrayList<PopupMenuItem>()
        //listItem.add(PopupMenuItem(AppConstants.EDIT, R.color.text_color_black, true))
        listItem.add(PopupMenuItem(AppConstants.CHANGE_PASSWORD, R.color.text_color_black, true))
        return listItem
    }


    /**
     *  Return DP value from pixel
     */
    fun getDpToPixel(dp: Int): Int {
        val scale: Float = (App.instance?.resources?.displayMetrics?.density ?: dp) as Float
        return (dp * scale + 0.5f).toInt()
    }

    /**
     * Returns the font category.
     */
    @JvmStatic
    fun getFontName(index: Int): String {
        return when (index) {
            2 -> "fonts/Bold.otf"
            3 -> "fonts/Italic.otf"
            4 -> "fonts/Medium.otf"
            5 -> "fonts/Semibold.otf"
            else -> "fonts/Regular.otf"
        }
    }

    /**
     * Return the default progress dialog
     */
    fun getProgressDialog(
        activity: Activity?,
        cancelableFlag: Boolean,
        strLoader: String
    ): Dialog? {
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.progress_layout)
        dialog.setCancelable(cancelableFlag)
        val tvLoaderText = dialog.findViewById(R.id.tvLoaderText) as TextView
        tvLoaderText.text = strLoader
        return dialog
    }

    /**
     * Function is used to add asterisk star symbol mark
     */
    fun asteriskMark(value: String): String {
        val colored = "*"
        val builder = SpannableStringBuilder(value + colored)

        builder.setSpan(
            ForegroundColorSpan(Color.RED), value.length, builder.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return builder.toString()
    }

    fun getCommaSeparatedRoles(roles: ArrayList<String>?): String {
        if (roles.isNullOrEmpty()) {
            return ""
        }

        return if (roles.size > 0) {
            var appendedString = ""
            var index = 0;
            for (item in roles) {
                if (index == 0) {
                    appendedString = "$item "
                    index++
                } else {
                    appendedString = "$appendedString, $item"
                }
            }
            appendedString
        } else {
            ""
        }
    }


    fun getFirstNameWithFirstCharacterOfLastName(fname: String?, lname: String?): String? {
        var name: String? = ""
        if (fname.isNullOrEmpty().not()) {
            name = fname
        }
        if (lname.isNullOrEmpty().not()) {
            if (lname != null) {
                name = "$name ${lname.toCharArray()[0]}."
            }
        }
        return name
    }

    fun getFirstNameAndLastNameFirstCharacter(fname: String?, lname: String?): String {
        var name: String = ""
        if (fname.isNullOrEmpty().not()) {
            name = fname?.replaceFirstChar { it.uppercase() }.toString()
        }
        if (lname.isNullOrEmpty().not()) {
            name = "$name " + lname?.get(0)
        }
        return name
    }

    fun getFirstLastName(fname: String?, lname: String?): String? {
        var name: String? = ""
        if (fname.isNullOrEmpty().not()) {
            name = fname?.replaceFirstChar { it.uppercase() }
        }
        if (lname.isNullOrEmpty().not()) {
            name = "$name ${lname?.replaceFirstChar { it.uppercase() }}"
        }
        return name
    }

    fun formatDate(newFormat: String, value: String): String? {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = sdf.parse(value)
            getDateTimeFromLong(newFormat, date.time.toString())
        } catch (e: Exception) {
            ""
        }
    }

    fun formatDate(newFormat: String, oldFormat: String, value: String): String? {
        return try {
            val sdf = SimpleDateFormat(oldFormat, Locale.ENGLISH)
            val date = sdf.parse(value)
            getDateTimeFromLong(newFormat, date.time.toString())
        } catch (e: Exception) {
            ""
        }
    }

    fun getDateTimeFromLong(pattern: String, longValue: String): String? {
        return try {
            val sdf = SimpleDateFormat(pattern, Locale.ENGLISH)
            val netDate = Date(longValue.toLong())
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun capitalize(str: String?): String {
        if (null == str) {
            return ""
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1)
    }

    fun formatCellNumber(mobile: String?): String {
        if (null == mobile) {
            return ""
        }
        var formattedMobile = mobile
        var lastTenDigit = mobile
        var countryCode = ""

        if (mobile.length > 10) {
            countryCode = mobile.substring(0, (mobile.length - 10))
            countryCode = if (countryCode == "+") {
                ""
            } else {
                "$countryCode-"
            }
            lastTenDigit = mobile.substring(mobile.length - 10)
            formattedMobile = countryCode + "" + lastTenDigit
        }
        return formattedMobile
    }

    fun getSessionLabel(input: String): String {
        return when (input) {
            "first-half" -> {
                "Session 1"
            }
            "second-half" -> {
                "Session 2"
            }
            else -> {
                ""
            }
        }
    }

    fun getGender(gender: String): String {
        return when (gender.uppercase()) {
            "M" -> "Male"
            "F" -> "Female"
            else -> "Other"
        }
    }

    fun getPositionByText(list: ArrayList<CustomSpinnerItem>, key: String): Int {
        val position = 0
        if (key.isNullOrEmpty()) {
            return position
        }
        list.forEachIndexed { index, element ->
            if (element.key.equals(key, ignoreCase = true)) {
                return index
            }
        }
        return position
    }

    fun getValueByKey(list: ArrayList<CustomSpinnerItem>, key: String): String {
        list.forEachIndexed { index, element ->
            if (element.key.equals(key, ignoreCase = true)) {
                return element.title
            }
        }
        return key
    }

    fun getPopupMenusBottomButtons(): ArrayList<PopupMenuItem> {
        val listItem = ArrayList<PopupMenuItem>()
        listItem.add(PopupMenuItem(AppConstants.SAVE_AND_EXIT, R.color.text_color_black, true))
        listItem.add(
            PopupMenuItem(
                AppConstants.SAVE_AND_ADD_ANOTHER,
                R.color.text_color_black,
                true
            )
        )
        return listItem
    }

    fun getCustomList(customFields: ArrayList<CustomFieldDTO>?): ArrayList<Model> {
        val list = ArrayList<Model>()
        for (item in customFields!!) {
            when {
                item.customField?.valueType.equals(AppConstants.CUSTOM_FIELD_STRING) -> {
                    list.add(Model(Model.CUSTOM_FIELD_STRING, item))
                }
                item.customField?.valueType.equals(AppConstants.CUSTOM_FIELD_INTEGER) -> {
                    list.add(Model(Model.CUSTOM_FIELD_INTEGER, item))
                }
                item.customField?.valueType.equals(AppConstants.CUSTOM_FIELD_SELECTION) -> {
                    list.add(Model(Model.CUSTOM_FIELD_DROPDOWN, item))
                }
            }
        }
        return list
    }

    fun getArrayListFromAnyObject(valueOptions: Any?): ArrayList<String> {
        val list = ArrayList<String>()
        if (valueOptions is ArrayList<*>) {
            for (item in valueOptions) {
                list.add(item as String)
            }
        }
        return list
    }

    fun isResource(user: User): Boolean {
        if (user.primaryRole.equals(AppConstants.PROJECT_OWNER, ignoreCase = true)) {
            return false
        }
        if (user.primaryRole.equals(AppConstants.PROJECT_PROJECT_OWNER, ignoreCase = true)) {
            return false
        }
        if (user.primaryRole.equals(AppConstants.ORG_FULL_ACCESS, ignoreCase = true)) {
            return false
        }
        return true
    }

    fun isOrgFullAccess(user: User): Boolean {
        if (user.primaryRole.equals(AppConstants.ORG_FULL_ACCESS, ignoreCase = true)) {
            return true
        }
        return false
    }

    fun getFullAddress(address: Address): String {
        /*val labelAddress =
                            "${address.line1},\n${address.line2},\n${address.city}, ${address.state},\n${address.country} - ${address.pin}"*/
        val sb = StringBuilder()
        if (address.line1.isNullOrEmpty().not()) {
            sb.append(address.line1 + ",")
            sb.append("\n")
        }
        if (address.line2.isNullOrEmpty().not()) {
            sb.append(address.line2 + ",")
            sb.append("\n")
        }
        if (address.city.isNullOrEmpty().not()) {
            sb.append(address.city?.replaceFirstChar { it.uppercase() } + ",")
            sb.append("\n")
        }
        if (address.state.isNullOrEmpty().not()) {
            sb.append(address.state?.replaceFirstChar { it.uppercase() } + ",")
            sb.append("\n")
        }
        if (address.country.isNullOrEmpty().not()) {
            sb.append(address.country?.replaceFirstChar { it.uppercase() } + ",")
            sb.append("\n")
        }
        if (address.pin.isNullOrEmpty().not()) {
            sb.append(address.pin)
            sb.append("\n")
        }
        return sb.toString()
    }

    fun getTimeDiff(
        startTime: String,
        startPosition: Int,
        endTime: String,
        endPosition: Int
    ): String {

        val differenceInPosition = endPosition - startPosition
        return calculateDiffByPosition(differenceInPosition)
    }

    private fun calculateDiffByPosition(differenceInPosition: Int): String {
        return when (differenceInPosition) {
            1 -> "00:00:30"
            2 -> "00:01:00"
            3 -> "00:01:30"
            4 -> "00:02:00"
            5 -> "00:02:30"
            6 -> "00:03:00"
            7 -> "00:03:30"
            8 -> "00:04:00"
            9 -> "00:04:30"
            10 -> "00:05:00"
            11 -> "00:05:30"
            12 -> "00:06:00"
            13 -> "00:06:30"
            14 -> "00:07:00"
            15 -> "00:07:30"
            16 -> "00:08:00"
            17 -> "00:08:30"
            18 -> "00:09:00"
            19 -> "00:09:30"
            20 -> "00:10:00"
            21 -> "00:10:30"
            22 -> "00:11:00"
            23 -> "00:11:30"
            24 -> "00:12:00"
            25 -> "00:12:30"
            26 -> "00:13:00"
            27 -> "00:13:30"
            28 -> "00:14:00"
            29 -> "00:14:30"
            30 -> "00:15:00"
            31 -> "00:15:30"
            32 -> "00:16:00"
            33 -> "00:16:30"
            34 -> "00:17:00"
            35 -> "00:17:30"
            36 -> "00:18:00"
            37 -> "00:18:30"
            38 -> "00:19:00"
            39 -> "00:19:30"
            40 -> "00:20:00"
            41 -> "00:20:30"
            42 -> "00:21:00"
            43 -> "00:21:30"
            44 -> "00:22:00"
            45 -> "00:22:30"
            46 -> "00:23:00"
            47 -> "00:23:30"
            48 -> "00:24:00"
            else ->
                ""
        }
    }

    fun getTimeAMPMByHHMMSS(valueParam: String): String {
        val list = getTimeListing()
        list.forEachIndexed { index, time ->
            if (valueParam == time.valueIn24Hour) {
                return time.value.toString()
            }
        }
        return ""
    }

    fun getTimePosition(valueParam: String): Int {
        val list = getTimeListing()
        list.forEachIndexed { index, time ->
            if (valueParam == time.valueIn24Hour) {
                return index
            }
        }
        return 0
    }

    fun getTimeListing(): ArrayList<Time> {
        val list = ArrayList<Time>()

        list.add(Time("12:00 AM", "00:00:00"))
        list.add(Time("12:30 AM", "00:30:00"))
        list.add(Time("01:00 AM", "01:00:00"))
        list.add(Time("01:30 AM", "01:30:00"))
        list.add(Time("02:00 AM", "02:00:00"))
        list.add(Time("02:30 AM", "02:30:00"))
        list.add(Time("03:00 AM", "03:00:00"))
        list.add(Time("03:30 AM", "03:30:00"))
        list.add(Time("04:00 AM", "04:00:00"))
        list.add(Time("04:30 AM", "04:30:00"))
        list.add(Time("05:00 AM", "05:00:00"))
        list.add(Time("05:30 AM", "05:30:00"))
        list.add(Time("06:00 AM", "06:00:00"))
        list.add(Time("06:30 AM", "06:30:00"))
        list.add(Time("07:00 AM", "07:00:00"))
        list.add(Time("07:30 AM", "07:30:00"))
        list.add(Time("08:00 AM", "08:00:00"))
        list.add(Time("08:30 AM", "08:30:00"))
        list.add(Time("09:00 AM", "09:00:00"))
        list.add(Time("09:30 AM", "09:30:00"))
        list.add(Time("10:00 AM", "10:00:00"))
        list.add(Time("10:30 AM", "10:30:00"))
        list.add(Time("11:00 AM", "11:00:00"))
        list.add(Time("11:30 AM", "11:30:00"))

        list.add(Time("12:00 PM", "12:00:00"))
        list.add(Time("12:30 PM", "12:30:00"))
        list.add(Time("01:00 PM", "13:00:00"))
        list.add(Time("01:30 PM", "13:30:00"))
        list.add(Time("02:00 PM", "14:00:00"))
        list.add(Time("02:30 PM", "14:30:00"))
        list.add(Time("03:00 PM", "15:00:00"))
        list.add(Time("03:30 PM", "15:30:00"))
        list.add(Time("04:00 PM", "16:00:00"))
        list.add(Time("04:30 PM", "16:30:00"))
        list.add(Time("05:00 PM", "17:00:00"))
        list.add(Time("05:30 PM", "17:30:00"))
        list.add(Time("06:00 PM", "18:00:00"))
        list.add(Time("06:30 PM", "18:00:00"))
        list.add(Time("07:00 PM", "19:00:00"))
        list.add(Time("07:30 PM", "19:30:00"))
        list.add(Time("08:00 PM", "20:00:00"))
        list.add(Time("08:30 PM", "20:30:00"))
        list.add(Time("09:00 PM", "21:00:00"))
        list.add(Time("09:30 PM", "21:30:00"))
        list.add(Time("10:00 PM", "22:00:00"))
        list.add(Time("10:30 PM", "22:30:00"))
        list.add(Time("11:00 PM", "23:00:00"))
        list.add(Time("11:30 PM", "23:30:00"))

        return list
    }
}
