package com.example.expensemanager.AuthFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager.*
import com.example.expensemanager.ViewModels.MainViewModel
import com.example.expensemanager.ViewModels.MainViewModelFactory
import com.example.expensemanager.databinding.FragmentOtpBinding
import com.example.expensemanager.models.RegisterData
import com.example.expensemanager.repository.Repository


class OtpFragment : Fragment() {
    private var binding:FragmentOtpBinding?=null
    private lateinit var viewModel: MainViewModel//for api communication
    private lateinit var sharedViewModel: SharedViewModel//for communicating between data
    var email:String="nomail"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentOtpBinding.inflate(inflater,container,false)
        sharedViewModel=ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val repository=Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel=ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]

        val sharedPref=this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()

        val name=sharedViewModel.getName()
        val email=sharedViewModel.getEmail()
        val password=sharedViewModel.getPassword()
        val hashedotp=sharedViewModel.getHashedOtp()


        binding?.btnSubmit?.setOnClickListener {
            val otp=binding?.editTextNumberPassword?.text.toString()

            viewModel.pushRegisterDetails(RegisterData(name,email,password,otp,hashedotp))

            viewModel.myRegisterDataResponse.observe(viewLifecycleOwner, Observer { response->
                if(response.isSuccessful){
                    editor?.putString("token",response.body()?.token.toString())
                    response.body()?.let { it1 -> editor?.putBoolean("success", it1.success) }
                    editor?.commit()

                    val loginStatus=sharedPref?.getBoolean("success",false)
                    if(loginStatus==true) {
                        editor?.putString("name",name)
                        val intent=Intent(requireActivity(),HomeActivity::class.java)
                        startActivity(intent)
                    }
                }
                else{
                    if(response.code()===404) {
                        Toast.makeText(activity,"Please Enter Correct OTP",Toast.LENGTH_LONG).show()
                    }
                    else if(response.code()===401){
                        Toast.makeText(activity,"User Already exists",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(activity,"Some unexpected error occured",Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
        return binding?.root
    }

    private fun replaceFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction().replace(R.id.fragment_Container,fragment).commit()
    }
}