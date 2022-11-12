package com.example.junoassessment.data.model


import com.google.gson.annotations.SerializedName

data class CryptoPrice(
    @SerializedName("current_price_in_usd")
    val currentPriceInUsd: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("title")
    val title: String?
)