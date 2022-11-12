package com.example.junoassessment.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("all_transactions")
    val allTransactions: List<AllTransaction>,
    @SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance,
    @SerializedName("crypto_prices")
    val cryptoPrices: List<CryptoPrice>,
    @SerializedName("your_crypto_holdings")
    val yourCryptoHoldings: List<YourCryptoHolding>
)