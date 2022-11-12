package com.example.junoassessment.data.repository.datasourceImpl

import com.example.junoassessment.data.api.JunoApiService
import com.example.junoassessment.data.repository.datasource.RemoteDataSource
import com.example.junoassessment.data.model.ApiResponse
import retrofit2.Response

class RemoteDataSourceImpl(
    private val junoApiService: JunoApiService
): RemoteDataSource {
    override suspend fun getEmptyStateResponse(): Response<ApiResponse> =
        junoApiService.getEmptyStateData()

    override suspend fun getValueStateResponse(): Response<ApiResponse> =
        junoApiService.getValueStateData()
}