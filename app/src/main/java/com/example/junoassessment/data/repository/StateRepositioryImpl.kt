package com.example.junoassessment.data.repository

import com.example.junoassessment.data.model.ApiResponse
import com.example.junoassessment.data.repository.datasource.RemoteDataSource
import com.example.junoassessment.data.util.Resource
import com.example.junoassessment.domain.repository.StateRepository
import retrofit2.Response

class StateRepositioryImpl(
    private val remoteDataSource: RemoteDataSource
): StateRepository {
    override suspend fun emptyState(): Resource<ApiResponse> =
        responseToResult(remoteDataSource.getEmptyStateResponse())

    override suspend fun valueState(): Resource<ApiResponse> =
        responseToResult(remoteDataSource.getValueStateResponse())

    private fun responseToResult(response:Response<ApiResponse>):Resource<ApiResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }else{
            return Resource.Error(response.message(), response.code())
        }
        return Resource.Error(response.message(), response.code())
    }
}