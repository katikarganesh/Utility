


fun isInternetConnected(): Boolean {
    with(App.instance?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
        getNetworkCapabilities(activeNetwork)?.let {
            return (it.hasCapability(NET_CAPABILITY_INTERNET) && it.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            ))
        }
    }
    return false
}
