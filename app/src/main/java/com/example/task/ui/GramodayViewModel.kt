package com.example.task.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.model.GramodayApiResponse
import com.example.task.repository.GramodayRepository
import com.example.task.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class GramodayViewModel(val repository: GramodayRepository) : ViewModel() {

    val apiData: MutableLiveData<Resource<GramodayApiResponse>> = MutableLiveData()

    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        apiData.postValue(Resource.Loading())
        val response = repository.getData()
        apiData.postValue(handleApiDataReponse(response))
    }

    private fun handleApiDataReponse(response: Response<GramodayApiResponse>): Resource<GramodayApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}