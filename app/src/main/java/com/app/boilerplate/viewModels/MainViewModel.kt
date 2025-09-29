package com.app.boilerplate.viewModels

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.boilerplate.Utils.PreferenceManager
import com.app.boilerplate.model.DemoClass
import com.app.boilerplate.repository.AuthRepository
import com.app.boilerplate.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel (var activity: Activity) : ViewModel() {

    private val preferenceManager: PreferenceManager = PreferenceManager(activity)
    private val mainRepository: MainRepository = MainRepository(activity)

    fun getDataApiCall(hashMap: HashMap<String, Any>): MutableLiveData<DemoClass> {

        var api : MutableLiveData<DemoClass> = MutableLiveData()

        viewModelScope.launch {
            api = mainRepository.getData(hashMap)
        }

        return api
    }
}