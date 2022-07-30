package com.example.expensemanager.AuthFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager.ViewModels.MainViewModel
import com.example.expensemanager.ViewModels.MainViewModelFactory
import com.example.expensemanager.R
import com.example.expensemanager.databinding.FragmentRegisterBinding
import com.example.expensemanager.models.SendOtpData
import com.example.expensemanager.repository.Repository


class RegisterFragment : Fragment(R.layout.fragment_register){
    private  var binding:FragmentRegisterBinding? = null
    private lateinit var viewModel: MainViewModel
//    private lateinit var communicator: Communicator
    private lateinit var sharedViewModel: SharedViewModel
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
            val loginText=binding?.loginTextView
            val btnRegister=binding?.btnRegister

            //initiallizing shared preferences for storing login tokens


            sharedViewModel= ViewModelProvider(requireActivity())[SharedViewModel::class.java]

            var repository= Repository()
            val viewModelFactory= MainViewModelFactory(repository)
            viewModel= ViewModelProvider(this,viewModelFactory) [MainViewModel::class.java]
            // send request to send otp for verification
            btnRegister?.setOnClickListener {
                val userEmail= binding?.editTextEmail?.text.toString()
                val userPassword=binding?.editTextPassword?.text.toString()
                val userConfirmPassword=binding?.editTextConfirmPassword?.text.toString()
                val userName=binding?.editTextName?.text.toString()

                if(userPassword!=userConfirmPassword){
                    Toast.makeText(activity,"confirm-password and password field did not match",Toast.LENGTH_SHORT).show()
                    Toast.makeText(activity,userPassword+" "+userConfirmPassword,Toast.LENGTH_SHORT).show()
                }
                else{
                    viewModel.pushSendOtp(SendOtpData(userEmail))
                    viewModel.mySendOtpResponse.observe(viewLifecycleOwner,Observer{response->

                        if(response.isSuccessful){
                            sharedViewModel.setEmail(userEmail)
                            sharedViewModel.setPassword(userPassword)
                            sharedViewModel.setName(userName)
                            sharedViewModel.setOtp(response.body()?.otp.toString())
                            sharedViewModel.setHashedOtp(response.body()?.hashedotp.toString())

                            Toast.makeText(activity,"OTP sent",Toast.LENGTH_SHORT).show()
                            replaceFragment(OtpFragment())
                        }
                        else{
                            Toast.makeText(activity,"OTP not sent",Toast.LENGTH_SHORT).show()
                            Log.d("Main",response.body().toString())
                        }
                    })
                }
            }
            loginText?.setOnClickListener {
                replaceFragment(LoginFragment())
            }
        return binding?.root
    }


    private fun replaceFragment(fragment:Fragment){
        val fragmetManager= parentFragmentManager
        val fragmentTransaction=fragmetManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_Container,fragment)
        fragmentTransaction.commit()
    }

    private fun handleLoginStatus(fragment: Fragment){
        val sharedPref=this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val loginStatus=sharedPref?.getBoolean("success",false)
        if(loginStatus==true) {
            replaceFragment(fragment)
        }
    }



}