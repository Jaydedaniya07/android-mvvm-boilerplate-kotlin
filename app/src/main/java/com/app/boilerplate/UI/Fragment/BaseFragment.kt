package com.app.boilerplate.UI.Fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.boilerplate.Utils.PreferenceManager

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    lateinit var preferenceManager : PreferenceManager

    lateinit var activity: Activity

    lateinit var binding: B

    private lateinit var keyboardLayoutListener: ViewTreeObserver.OnGlobalLayoutListener

    abstract fun getLayout(): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getLayout()
        preferenceManager = PreferenceManager(requireContext())
        initView()
        activity = requireActivity()
        return binding.root
    }

    abstract fun initView()

    override fun onDestroy() {
        binding.root.viewTreeObserver.removeOnGlobalLayoutListener(keyboardLayoutListener)
        super.onDestroy()
    }
}