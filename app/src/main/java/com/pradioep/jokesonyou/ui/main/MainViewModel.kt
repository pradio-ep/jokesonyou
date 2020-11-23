package com.pradioep.jokesonyou.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.model.Search
import com.pradioep.jokesonyou.repository.Service
import com.pradioep.jokesonyou.ui.base.BaseViewModel
import com.pradioep.jokesonyou.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(private val service: Service): BaseViewModel() {

    val clickBack = SingleLiveEvent<Unit>()
    val clickClose = SingleLiveEvent<Unit>()
    val listSearch = MutableLiveData<ArrayList<Result>>()
    val listCategory = MutableLiveData<ArrayList<String>>()

    fun onClickBack() {
        clickBack.call()
    }

    fun onClickClose() {
        clickClose.call()
    }

    fun searchJokes(keyword: String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = service.search(keyword)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listSearch.value = response.body.result
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    serverError.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }

    fun getCategories() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = service.categories()) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    listCategory.value = response.body
                }
                is NetworkResponse.ServerError -> {
                    isLoading.value = false
                    serverError.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }
}