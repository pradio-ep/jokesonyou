package com.pradioep.jokesonyou.repository

import com.haroldadmin.cnradapter.NetworkResponse
import com.pradioep.jokesonyou.model.Error
import com.pradioep.jokesonyou.model.Result
import com.pradioep.jokesonyou.model.Search
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("search")
    suspend fun search(@Query("query") query: String)
            : NetworkResponse<Search, Error>

    @GET("categories")
    suspend fun categories(): NetworkResponse<ArrayList<String>, Error>

    @GET("random")
    suspend fun randomByCategory(@Query("category") category: String)
            : NetworkResponse<Result, Error>
}

open class Repository(private val service: Service) {

    suspend fun search(query: String): NetworkResponse<Search, Error> {
        return service.search(query)
    }

    suspend fun categories(): NetworkResponse<ArrayList<String>, Error> {
        return service.categories()
    }

    suspend fun randomByCategory(category: String): NetworkResponse<Result, Error> {
        return service.randomByCategory(category)
    }
}