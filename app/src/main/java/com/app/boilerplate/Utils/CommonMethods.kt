package com.app.boilerplate.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.app.boilerplate.R
import com.app.boilerplate.UI.App

object CommonMethods {

    fun <T> loadActivityBottomToTop(activity: Activity, java: Class<T>?) {
        activity.startActivity(Intent(activity, java))
        activity.overridePendingTransition(R.animator.slide_up, R.animator.no_anim)
    }

    fun loadActivityBottomToTopWithData(activity: Activity, intent: Intent?) {
        activity.startActivity(intent)
        activity.overridePendingTransition(R.animator.slide_up, R.animator.no_anim)
    }

    fun <T> loadActivityRightToLeft(activity: Activity, java: Class<T>?) {
        activity.startActivity(Intent(activity, java))
        activity.overridePendingTransition(R.animator.enter_from_right, R.animator.no_anim)
    }

    fun loadActivityRightToLeftWithData(activity: Activity, intent: Intent?) {
        activity.startActivity(intent)
        activity.overridePendingTransition(R.animator.enter_from_right, R.animator.no_anim)
    }

    fun onBackPressedTopToBottom(activity: Activity) {
        activity.overridePendingTransition(R.animator.no_anim, R.animator.slide_down)
    }

    fun onBackPressedLeftToRight(activity: Activity) {
        activity.overridePendingTransition(R.animator.no_anim, R.animator.exit_to_right)
    }

    fun closeKeyboard(fa: FragmentActivity) {
        try {
            val imm: InputMethodManager =
                fa.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (!imm.hideSoftInputFromWindow(
                    fa.currentFocus?.windowToken, 0
                )
            ) if (!imm.hideSoftInputFromWindow(
                    fa.currentFocus?.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            ) imm.hideSoftInputFromWindow(
                fa.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (e: Exception) {
        }
    }

    fun getVersionName(context: Context): Any? {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return -1 // Return -1 if there was an error
    }

    fun <T> T.showToast(context: Context) {
        Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()
    }

    fun <T> T.addLogs(context: Context) {
        Log.d("checkingData", "check value: ${this.toString()}")
    }

    fun getDeviceName() = "${Build.MANUFACTURER} ${Build.MODEL}"

    fun checkInternet(context: Context, handle: () -> Unit) {
        if (App.isNetworkAvailable)
            handle()
    }
}