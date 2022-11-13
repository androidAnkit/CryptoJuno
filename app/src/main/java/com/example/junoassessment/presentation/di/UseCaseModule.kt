package com.example.junoassessment.presentation.di

import com.example.junoassessment.domain.repository.StateRepository
import com.example.junoassessment.domain.usecase.GetEmptyUseCase
import com.example.junoassessment.domain.usecase.GetValueStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetEmptyStateUseCase(stateRepository: StateRepository):GetEmptyUseCase =
        GetEmptyUseCase(stateRepository)

    @Singleton
    @Provides
    fun provideGetValueStateUseCase(stateRepository: StateRepository):GetValueStateUseCase =
        GetValueStateUseCase(stateRepository)
}