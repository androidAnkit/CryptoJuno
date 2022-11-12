package com.example.junoassessment.data.model


import com.google.gson.annotations.SerializedName

data class YourCryptoHolding(
    @SerializedName("current_bal_in_token")
    val currentBalInToken: String?,
    @SerializedName("current_bal_in_usd")
    val currentBalInUsd: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("title")
    val title: String?
)