package com.example.junoassessment.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.junoassessment.domain.usecase.GetEmptyUseCase

class EmptyStateViewModelFactory
    (
    private val app: Application,
    private val getEmptyUseCase: GetEmptyUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
         EmptyStateViewModel(app, getEmptyUseCase) as T

}