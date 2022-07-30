package com.example.expensemanager.AuthFragments

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager.*
import com.example.expensemanager.ViewModels.MainViewModel
import com.example.expensemanager.ViewModels.MainViewModelFactory
import com.example.expensemanager.databinding.FragmentLoginBinding
import com.example.expensemanager.models.LoginData
import com.example.expensemanager.repository.Repository

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var binding: FragmentLoginBinding? =null
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(inflater,container,false)
        val view=binding?.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btnLogin=binding?.btnLogin
        var registerText=binding?.registerTextView
        var repository= Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel= ViewModelProvider(this,viewModelFactory) [MainViewModel::class.java]

        //initiallizing shared preferences for storing login tokens
        val sharedPref=this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()

        btnLogin?.setOnClickListener{
            binding?.progressBar?.isVisible=true

            var userEmail=binding?.emailEditText?.text.toString()
            var userPassword=binding?.passwordEditText?.text.toString()

//            binding?.editTextEmail?.setHintTextColor()
            binding?.emailEditText?.setHintTextColor(ContextCompat.getColorStateList(requireContext(),R.color.edittextcolorstatelist))
//            binding?.editTextEmail?.setHintTextColor(ContextCompat.getColorStateList(requireContext(),R.color.edittextcolorstatelist))

            val loginData= LoginData(userEmail,userPassword)
            viewModel.getLoginDetails(loginData)
            viewModel.myresponse.observe(viewLifecycleOwner, Observer {response->
                Log.d("Main",response.toString())
                if(response.isSuccessful){
                    editor?.putString("token",response.body()?.token.toString())
                    editor?.putString("name",response.body()?.name.toString())
                    response.body()?.let { it1 -> editor?.putBoolean("success", it1.success) }
                    editor?.commit()
                    val loginStatus=sharedPref?.getBoolean("success",false)
                    binding?.progressBar?.isVisible=false

                    if(loginStatus==true) {
                        val intent=Intent(requireActivity(),HomeActivity::class.java)
                        startActivity(intent)
                    }
                }
                else{
                    binding?.progressBar?.isVisible=false
                    Log.d("Main","Response unsuccessfull")
                    Toast.makeText(activity,"Response unsuccessfull",Toast.LENGTH_SHORT).show()
                }
            })
        }
        registerText?.setOnClickListener {
            replaceFragment(RegisterFragment())
        }
        //checking whether user has received login token or not
        val loginStatus=sharedPref?.getBoolean("success",false)
        Toast.makeText(activity,loginStatus.toString(),Toast.LENGTH_SHORT)

//        if(loginStatus==true) return replaceFragment(HomeFragment())


    }
        //this function is used to replace one frament with another fragment
        private fun replaceFragment(fragment:Fragment){
            val fragmetManager= parentFragmentManager
            val fragmentTransaction=fragmetManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_Container,fragment)
            fragmentTransaction.commit()
        }
}