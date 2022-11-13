package com.example.junoassessment.presentation.di

import android.app.Application
import com.example.junoassessment.presentation.viewModel.BuyViewModelFactory
import com.example.junoassessment.presentation.viewModel.NoInternetViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class BuyFactoryModule {
    @ActivityScoped
    @Provides
    fun provideBuyFactory(app:Application): BuyViewModelFactory =
        BuyViewModelFactory(app)
}