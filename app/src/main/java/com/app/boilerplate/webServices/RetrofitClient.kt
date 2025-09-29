package com.app.boilerplate.webServices

import com.app.boilerplate.UI.App
import com.app.boilerplate.Utils.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    private lateinit var retrofit: Retrofit

    fun get(): ApiServices {

        val gson = GsonBuilder()
            .disableHtmlEscaping()
            .setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(ApiServices::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.callTimeout(5, TimeUnit.MINUTES)
        builder.connectTimeout(5, TimeUnit.MINUTES)
        builder.readTimeout(5, TimeUnit.MINUTES)
        builder.writeTimeout(5, TimeUnit.MINUTES)
        builder.addNetworkInterceptor(httpLoggingInterceptor)
        builder.addInterceptor { chain ->
            val request = chain.request()
            if (App.sessionId.isNotEmpty()) {
                val header = request.newBuilder().header(
                    "Authorization",
                    App.preferenceManager.accessToken
                )
                val build = header.build()
                chain.proceed(build)
            } else
                chain.proceed(request)
        }
        return builder.build()
    }

    fun getRetrofitInstance(): Retrofit {

        val gson = GsonBuilder()
            .disableHtmlEscaping()
            .setLenient().create()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_API_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit
    }
}