package com.example.junoassessment.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CryptoBalance(
    @SerializedName("current_bal_in_usd")
    val currentBalInUsd: String?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?
):Serializable