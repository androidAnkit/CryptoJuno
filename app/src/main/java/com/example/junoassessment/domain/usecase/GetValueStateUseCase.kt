package com.example.junoassessment.domain.usecase

import com.example.junoassessment.data.model.ApiResponse
import com.example.junoassessment.data.util.Resource
import com.example.junoassessment.domain.repository.StateRepository

class GetValueStateUseCase(private val stateRepository: StateRepository) {
    suspend fun execute(): Resource<ApiResponse> =
        stateRepository.valueState()
}