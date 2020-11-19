package com.pradioep.jokesonyou.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
    val showMessage : MutableLiveData<Any> = MutableLiveData()
    val serverError : MutableLiveData<Any> = MutableLiveData()
    val networkError : MutableLiveData<Any> = MutableLiveData()
}