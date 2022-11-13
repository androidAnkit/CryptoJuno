package com.example.junoassessment.presentation.di

import com.example.junoassessment.data.repository.StateRepositioryImpl
import com.example.junoassessment.data.repository.datasource.RemoteDataSource
import com.example.junoassessment.domain.repository.StateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideJunoRepository(
        remoteDataSource: RemoteDataSource
    ): StateRepository =
        StateRepositioryImpl(remoteDataSource)
}
