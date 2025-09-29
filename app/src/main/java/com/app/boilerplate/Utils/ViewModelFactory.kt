package com.app.boilerplate.Utils

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.boilerplate.viewModels.AuthViewModel
import com.app.boilerplate.viewModels.MainViewModel

class ViewModelFactory(var activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        when (modelClass) {
            AuthViewModel::class.java -> {
                return AuthViewModel(activity) as T
            }

            MainViewModel::class.java -> {
                return MainViewModel(activity) as T
            }

            else -> {
                return super.create(modelClass)
            }
        }
    }
}