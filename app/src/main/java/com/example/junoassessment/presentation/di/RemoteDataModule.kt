package com.example.junoassessment.presentation.di

import com.example.junoassessment.data.api.JunoApiService
import com.example.junoassessment.data.repository.datasource.RemoteDataSource
import com.example.junoassessment.data.repository.datasourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(junoApiService: JunoApiService): RemoteDataSource =
        RemoteDataSourceImpl(junoApiService)
}