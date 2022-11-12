package com.example.junoassessment.data.api

import com.example.junoassessment.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JunoApiService {

    // Fetch value state data
    @GET("empty-home")
    suspend fun getEmptyStateData(): Response<ApiResponse>

    // Fetch Value state data
    @GET("home")
    suspend fun getValueStateData(): Response<ApiResponse>
}