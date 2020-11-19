package com.pradioep.jokesonyou.model

import com.google.gson.annotations.SerializedName

data class Search (
    @SerializedName("total")
    val total: Int,
    @SerializedName("result")
    val result: ArrayList<Result>
)

data class Result (
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("categories")
    val categories: ArrayList<String>,
    @SerializedName("icon_url")
    val icon_url: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("value")
    val value: String
)