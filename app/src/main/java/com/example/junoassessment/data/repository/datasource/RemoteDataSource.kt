package com.example.junoassessment.data.repository.datasource

import com.example.junoassessment.data.model.ApiResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getEmptyStateResponse(): Response<ApiResponse>
    suspend fun getValueStateResponse(): Response<ApiResponse>
}