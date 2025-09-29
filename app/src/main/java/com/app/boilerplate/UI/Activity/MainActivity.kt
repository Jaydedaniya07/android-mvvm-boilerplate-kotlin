package com.app.boilerplate.UI.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.app.boilerplate.databinding.ActivityMainBinding
import com.app.boilerplate.interfaces.OnClickHandler

class MainActivity : BaseActivity<ActivityMainBinding>(), OnClickHandler {

    // private lateinit var viewModel: MainViewModel
    
    override fun getLayout(inflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setOnClickHandler = this
        // viewModel = ViewModelProvider(this, ViewModelFactory(activity))[MainViewModel::class.java]
    }

    override fun initView() {


    }

    override fun onViewClicked(view: View) {
        when(view.id){

        }
    }
}