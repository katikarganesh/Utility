package com.instazen.app.apis

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import com.instazen.app.App


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