package com.example.expensemanager.AuthFragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _userEmail = MutableLiveData("noEmailStored")
    val userEmail: LiveData<String> = _userEmail

    private val _userPassword = MutableLiveData("noPassStored")
    val userPassword: LiveData<String> = _userPassword

    private val _userOtp = MutableLiveData("noOtpStored")
    val userOtp: LiveData<String> = _userOtp

    private val _userHashedOtp = MutableLiveData("noHashedOtpStored")
    val userHashedOtp: LiveData<String> = _userHashedOtp
    private val _userName = MutableLiveData("noUserName")
    val userName: LiveData<String> = _userName


    fun setName(newName: String) {
        _userName.value = newName
    }

    fun getName(): String {
        return _userName.value.toString()
    }

    fun setEmail(newEmail: String) {
        _userEmail.value = newEmail
    }

    fun getEmail(): String {
        val newemail = _userEmail.value
        return newemail.toString()
    }

    fun setPassword(newPassword: String) {
        _userPassword.value = newPassword
    }

    fun getPassword(): String {
        val newPassword = _userPassword.value
        return newPassword.toString()
    }

    fun setOtp(newOtp: String) {
        _userOtp.value = newOtp
    }

    fun getOtp(): String {
        return _userOtp.value.toString()
    }

    fun setHashedOtp(newHashedOtp: String) {
        _userHashedOtp.value = newHashedOtp
    }

    fun getHashedOtp(): String {
        return _userHashedOtp.value.toString()
    }


}