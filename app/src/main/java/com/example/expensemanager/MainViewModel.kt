package com.example.expensemanager

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.models.LoginData
import com.example.expensemanager.models.LoginResponse
import com.example.expensemanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    val myresponse:MutableLiveData<Response<LoginResponse>>  = MutableLiveData()

    fun getLoginDetails(login: LoginData){
        viewModelScope.launch {
            val response=repository.getLoginDetails(login)
            myresponse.value=response
        }
    }

}