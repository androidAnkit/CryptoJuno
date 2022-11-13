package com.example.junoassessment.presentation.di

import com.example.junoassessment.presentation.adapter.CryptoPriceAdapter
import com.example.junoassessment.presentation.adapter.TransactionAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TransactionAdapterModule {

    @Singleton
    @Provides
    fun provideTransactionAdapter(): TransactionAdapter =
        TransactionAdapter()
}