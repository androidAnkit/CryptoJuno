package com.example.junoassessment.presentation.viewModel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.example.junoassessment.data.model.ApiResponse
import com.example.junoassessment.data.util.Constants
import com.example.junoassessment.data.util.Resource
import com.example.junoassessment.domain.usecase.GetValueStateUseCase
import com.example.junoassessment.presentation.activity.NoInternetActivity
import kotlinx.coroutines.launch

class ValueStateViewModel
    (
    private val app: Application,
    private val getValueStateUseCase: GetValueStateUseCase
) : AndroidViewModel(app) {

    val valueStateData: MutableLiveData<Resource<ApiResponse>?> = MutableLiveData()
    fun getValueUseCaseData() =
        viewModelScope.launch {
            valueStateData.postValue(Resource.Loading())
            var apiResult: Resource<ApiResponse>? = null
            try {
                if (isNetworkAvailable(app)) {
                    apiResult = getValueStateUseCase.execute()
                    valueStateData.postValue(apiResult)
                } else {
                    valueStateData.postValue(null)
                    val noInternet = Intent(app, NoInternetActivity::class.java)
                    noInternet.putExtra(Constants.VALUE_STATE, true)
                    noInternet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    app.startActivity(noInternet)
                }
            } catch (e: Exception) {
                valueStateData.postValue(
                    Resource.Error(
                        e.message.toString(),
                        apiResult?.code
                    )
                )
            }
        }

    fun isNetworkAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return result
    }

}

