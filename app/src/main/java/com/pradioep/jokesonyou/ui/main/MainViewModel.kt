package com.pradioep.jokesonyou.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.repository.Service
import com.pradioep.jokesonyou.ui.base.BaseViewModel
import com.pradioep.jokesonyou.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(private val service: Service): BaseViewModel() {

    val listSearch = MutableLiveData<ArrayList<Result>>()
    val listCategory = MutableLiveData<ArrayList<String>>()
    val notFound = SingleLiveEvent<Unit>()
    val somethingWrong = SingleLiveEvent<Unit>()
    val clickClose = SingleLiveEvent<Unit>()

    fun searchJokes(keyword: String) {
        isLoadingSearch.value = true
        viewModelScope.launch {
            when (val response = service.search(keyword)) {
                is NetworkResponse.Success -> {
                    isLoadingSearch.value = false
                    val result = response.body.result
                    if (result.isEmpty()) {
                        notFound.call()
                    } else {
                        listSearch.value = result
                    }
                }
                is NetworkResponse.ServerError -> {
                    isLoadingSearch.value = false
                    serverError.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoadingSearch.value = false
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
                    somethingWrong.call()
                    serverError.value = response.body?.message
                }
                is NetworkResponse.NetworkError -> {
                    isLoading.value = false
                    somethingWrong.call()
                    networkError.value = response.error.message.toString()
                }
            }
        }
    }

    fun onClickClose() {
        clickClose.call()
    }
}