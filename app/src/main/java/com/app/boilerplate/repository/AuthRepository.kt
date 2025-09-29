package com.app.boilerplate.repository

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.app.boilerplate.model.DemoClass
import com.app.boilerplate.webServices.ApiServices
import com.app.boilerplate.webServices.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(val activity: Activity) {

    companion object {
        lateinit var onError: OnError
    }

    private var apiServices: ApiServices = RetrofitClient().get()

    fun login(hashMap: HashMap<String, Any>): MutableLiveData<DemoClass> {

        val userData: MutableLiveData<DemoClass> = MutableLiveData()
        val request = apiServices.login(hashMap)

        try {
            request.enqueue(object : Callback<DemoClass> {
                override fun onResponse(
                    call: Call<DemoClass>,
                    response: Response<DemoClass>
                ) {
                    if (response.isSuccessful) {
                        userData.value = response.body()
                    } else {
                        onError.onApiError(response.code(), response.errorBody())
                    }
                }

                override fun onFailure(call: Call<DemoClass>, t: Throwable) {
                    println("checkingData onFailure : ${t.message}")
                    userData.value = null
                }
            })
        } catch (e: Exception) {
            println("checkingData catch : ${e.message}")
            userData.value = null
        }

        return userData
    }

    interface OnError {
        fun onApiError(errorCode: Int, responseBody: ResponseBody?)
    }
}