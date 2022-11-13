package com.example.junoassessment.presentation.di

import android.app.Application
import com.example.junoassessment.domain.usecase.GetEmptyUseCase
import com.example.junoassessment.presentation.viewModel.EmptyStateViewModel
import com.example.junoassessment.presentation.viewModel.EmptyStateViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class EmptyStateFactoryModule {

    @ActivityScoped
    @Provides
    fun provideEmptyStateFactoryModule(
        application: Application,
        getEmptyUseCase: GetEmptyUseCase
    ): EmptyStateViewModelFactory =
        EmptyStateViewModelFactory(application, getEmptyUseCase)
}