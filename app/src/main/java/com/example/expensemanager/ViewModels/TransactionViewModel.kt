package com.example.expensemanager.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensemanager.models.*
import com.example.expensemanager.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class TransactionViewModel(private val repository: Repository) :ViewModel(){
    val myAddTransactionResponse: MutableLiveData<Response<TransactionResponse>> = MutableLiveData()
    val mygetMontlyIncomeAndExpense: MutableLiveData<Response<getMontlyIncomeAndExpenseResponse>> = MutableLiveData()
    val myGetMontlyTransaction:MutableLiveData<Response<List<TransactionX>>> = MutableLiveData()

    fun addTransaction(transactionData: TransactionData,auth:String){
        viewModelScope.launch {
            val response=repository.addTransaction(transactionData,auth)
            myAddTransactionResponse.value=response
        }
    }
    fun getMontlyIncomeAndExpense(body: getMontlyIncomeAndExpenseData, auth: String){
        viewModelScope.launch {
            val response=repository.getMontlyIncomeAndExpense(body,auth)
            mygetMontlyIncomeAndExpense.value=response
        }
    }
    fun getMontlyTransactions(body: getTransactionData, auth: String){
        viewModelScope.launch {
            val response=repository.getMonthlyTransactions(body,auth)
            myGetMontlyTransaction.value=response
        }
    }
}