package com.example.junoassessment.domain.repository

import com.example.junoassessment.data.model.ApiResponse
import com.example.junoassessment.data.util.Resource
import retrofit2.Response

interface StateRepository {
    suspend fun emptyState(): Resource<ApiResponse>
    suspend fun valueState(): Resource<ApiResponse>
}