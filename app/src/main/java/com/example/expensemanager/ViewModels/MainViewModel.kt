package com.example.expensemanager.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.models.*
import com.example.expensemanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    val myresponse:MutableLiveData<Response<LoginResponse>>  = MutableLiveData()
    val myRegisterDataResponse:MutableLiveData<Response<RegisterResponse>> = MutableLiveData()
    val mySendOtpResponse:MutableLiveData<Response<SendOtpResponse>> = MutableLiveData()

    fun getLoginDetails(login: LoginData){
        viewModelScope.launch {
            val response=repository.getLoginDetails(login)
            myresponse.value=response
        }
    }
    fun pushRegisterDetails(registerData:RegisterData){
        viewModelScope.launch {
            val response=repository.pushRegisterDetails(registerData)
            myRegisterDataResponse.value=response
        }
    }
    fun pushSendOtp(sendOtpData: SendOtpData){
        viewModelScope.launch {
            val response=repository.pushSendOtp(sendOtpData)
            mySendOtpResponse.value=response
        }
    }
}