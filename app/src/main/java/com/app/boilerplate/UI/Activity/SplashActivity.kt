package com.app.boilerplate.UI.Activity

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import com.app.boilerplate.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getLayout(inflater: LayoutInflater) = ActivitySplashBinding.inflate(layoutInflater)

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2500)
    }
}