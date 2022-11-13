package com.example.junoassessment.presentation.di

import com.example.junoassessment.presentation.adapter.CryptoHoldingAdapter
import com.example.junoassessment.presentation.adapter.CryptoPriceAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CryptoPriceAdapterModule {

    @Singleton
    @Provides
    fun provideCryptoPriceAdapter(): CryptoPriceAdapter =
        CryptoPriceAdapter()
}