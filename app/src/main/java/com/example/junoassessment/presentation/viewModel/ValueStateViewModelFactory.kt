package com.example.junoassessment.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.junoassessment.domain.usecase.GetEmptyUseCase
import com.example.junoassessment.domain.usecase.GetValueStateUseCase

class ValueStateViewModelFactory
    (
    private val app: Application,
    private val getValueStateUseCase: GetValueStateUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
         ValueStateViewModel(app, getValueStateUseCase) as T

}