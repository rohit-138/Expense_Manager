package com.example.expensemanager

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
import com.example.expensemanager.databinding.FragmentRegisterBinding
import com.example.expensemanager.models.RegisterData
import com.example.expensemanager.repository.Repository


class RegisterFragment : Fragment(R.layout.fragment_register){
    private  var binding:FragmentRegisterBinding? = null
    private lateinit var viewModel:MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginText=binding?.loginTextView
        val btnRegister=binding?.btnRegister

        //initiallizing shared preferences for storing login tokens
        val sharedPref=this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()

        var repository= Repository()
        val viewModelFactory=MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory) [MainViewModel::class.java]

        btnRegister?.setOnClickListener{
            val userEmail= binding?.editTextEmail?.text.toString()
            val userPassword=binding?.editTextPassword?.text.toString()
            val userConfirmPassword=binding?.editTextConfirmPassword?.text.toString()

            val registerData= RegisterData(userEmail,userPassword)
            if(userPassword==userConfirmPassword){
                viewModel.pushRegisterDetails(registerData)
                viewModel.myRegisterDataResponse.observe(viewLifecycleOwner, Observer{response->
                    Log.d("Main",response.toString())
                    if(response.isSuccessful){
                        editor?.putString("token",response.body()?.token.toString())
                        response.body()?.success?.let { it1 -> editor?.putBoolean("success", it1) }
                        editor?.commit()
                        handleLoginStatus(HomeFragment())
                    }
                    else{
                        Toast.makeText(activity,"registration not done",Toast.LENGTH_SHORT).show()
                        Log.d("Main",response.toString())
                    }
                })
               handleLoginStatus(HomeFragment())
            }
            else{
                Toast.makeText(activity,"Password did not match please enter the same password",Toast.LENGTH_SHORT).show()
            Toast.makeText(activity,userPassword+" "+userConfirmPassword,Toast.LENGTH_SHORT).show()
            }
        }

        loginText?.setOnClickListener {
            replaceFragment(LoginFragment())
        }
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