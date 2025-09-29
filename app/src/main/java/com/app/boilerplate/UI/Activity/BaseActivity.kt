package com.app.boilerplate.UI.Activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.app.boilerplate.Utils.PreferenceManager

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    lateinit var preferenceManager : PreferenceManager

    lateinit var binding: B

    lateinit var activity: Activity

    lateinit var context: Context

    abstract fun getLayout(inflater: LayoutInflater): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getLayout(layoutInflater)
        setContentView(binding.root)
        activity = this
        context = this
        preferenceManager = PreferenceManager(this)

        initView()
    }

    abstract fun initView()
}