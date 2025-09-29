package com.app.boilerplate.Utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.lang.ref.WeakReference

class PreferenceManager(context: Context) {

    val PREFS_FILENAME = "com.app.boilerplate.prefs"
    private var mSharedPreferences: SharedPreferences
    private var mEditor: SharedPreferences.Editor
    private val ACCESS_TOKEN = "accessToken"

    init {
        mSharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        mEditor = mSharedPreferences.edit()
    }

    fun isLogin(): Boolean {
        return mSharedPreferences.getBoolean("isLogin", false)
    }

    fun setLogin(isLogin: Boolean) {
        mEditor.putBoolean("isLogin", isLogin)
        mEditor.apply()
    }

    var accessToken
        get() = mSharedPreferences.getString(ACCESS_TOKEN, "") ?: ""
        set(value) {
            mEditor.putString(ACCESS_TOKEN, value)
            mEditor.apply()
        }


    /**
     * Called to save supplied value in shared preferences against given key.
     *
     * @param context Context of caller activity
     * @param key     Key of value to save against
     * @param value   Value to save
     */
    fun saveToPrefs(context: Context, key: String, value: Any) {
        val contextWeakReference = WeakReference(context)
        contextWeakReference.get()?.let {
            val editor = mSharedPreferences.edit()
            when (value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Boolean -> editor.putBoolean(key, value)
                is Long -> editor.putLong(key, value)
                is Float -> editor.putFloat(key, value)
                is Double -> editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
            }
            editor.apply()
        }
    }


    /**
     * Called to retrieve required value from shared preferences, identified by given key.
     * Default value will be returned if no value found or error occurred.
     *
     * @param context      Context of caller activity
     * @param key          Key to find value against
     * @param defaultValue Value to return if no data found against given key
     * @return Return the value found against given key, default if not found or any error occurs
     */
    fun getFromPrefs(context: Context, key: String, defaultValue: Any): Any {
        val contextWeakReference = WeakReference(context)
        contextWeakReference.get()?.let {
            try {
                return when (defaultValue) {
                    is String -> mSharedPreferences.getString(key, defaultValue) ?: defaultValue
                    is Int -> mSharedPreferences.getInt(key, defaultValue)
                    is Boolean -> mSharedPreferences.getBoolean(key, defaultValue)
                    is Long -> mSharedPreferences.getLong(key, defaultValue)
                    is Float -> mSharedPreferences.getFloat(key, defaultValue)
                    is Double -> java.lang.Double.longBitsToDouble(mSharedPreferences.getLong(key, java.lang.Double.doubleToLongBits(defaultValue)))
                    else -> defaultValue
                }
            } catch (e: Exception) {
                Log.e("Exception", e.message ?: "")
                return defaultValue
            }
        }
        return defaultValue
    }


    /**
     * @param context Context of caller activity
     * @param key     Key to delete from SharedPreferences
     */
    fun removeFromPrefs(context: Context, key: String) {
        val contextWeakReference = WeakReference(context)
        contextWeakReference.get()?.let {
            val editor = mSharedPreferences.edit()
            editor.remove(key)
            editor.apply()
        }
    }

    fun removeAllFromPrefs(context: Context, isLogIn: String, token: String) {
        val contextWeakReference = WeakReference(context)
        contextWeakReference.get()?.let {
            val editor = mSharedPreferences.edit()
            editor.remove(isLogIn)
            editor.remove(token)
            editor.apply()
        }
    }


    /**
     * Clear all the preferences
     *
     * @param context Application context / activity / fragment
     */
    fun clearFromPrefs(context: Context) {
        // removeFromPrefs(context, Constants.IS_LOGIN)
        // removeFromPrefs(context, Constants.FIRST_LOGIN)
    }

    fun hasKey(context: Context, key: String): Boolean {
        val contextWeakReference = WeakReference(context)
        contextWeakReference.get()?.let {
            return mSharedPreferences.contains(key)
        }
        return false
    }
}