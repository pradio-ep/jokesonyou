package com.pradioep.jokesonyou.model

import com.google.gson.annotations.SerializedName

data class Error (
    @SerializedName("status")
    val status: Int,
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String
)