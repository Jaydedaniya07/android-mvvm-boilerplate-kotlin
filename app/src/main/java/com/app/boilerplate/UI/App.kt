package com.app.boilerplate.UI

import android.app.Application
import com.app.boilerplate.Utils.NetworkCallback
import com.app.boilerplate.Utils.PreferenceManager

class App : Application(){

    companion object{
        lateinit var preferenceManager: PreferenceManager
        lateinit var sessionId: String
        var isNetworkAvailable: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        preferenceManager = PreferenceManager(this)
        sessionId = preferenceManager.accessToken
        NetworkCallback.init(this)
    }
}