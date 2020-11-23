package com.pradioep.jokesonyou.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.repository.Service
import com.pradioep.jokesonyou.ui.base.BaseViewModel
import com.pradioep.jokesonyou.util.SingleLiveEvent
import kotlinx.coroutines.launch

class DetailViewModel(private val service: Service): BaseViewModel() {

    val joke = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val randomJoke = MutableLiveData<Result>()
    val clickClose = SingleLiveEvent<Unit>()

    fun getRandomJokeByCategory(category: String) {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = service.randomByCategory(category)) {
                is NetworkResponse.Success -> {
                    isLoading.value = false
                    randomJoke.value = response.body
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

    fun onClickClose() {
        clickClose.call()
    }
}