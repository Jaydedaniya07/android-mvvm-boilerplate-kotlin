package com.app.boilerplate.Utils

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import com.app.boilerplate.UI.App

class NetworkCallback private constructor(application: Application) {

    companion object {
        fun init(application: Application) {
            NetworkCallback(application)
        }
    }

    init {
        val networkCallback =
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    App.isNetworkAvailable = true

                    application.sendBroadcast(Intent("networkCallback"))
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    Log.d("ON_LOST", network.toString())
                    App.isNetworkAvailable = false
                    application.sendBroadcast(Intent("networkCallback"))
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    App.isNetworkAvailable = false
                    application.sendBroadcast(Intent("networkCallback"))
                }
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val connectivityManager =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        }
    }
}