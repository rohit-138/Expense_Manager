package com.example.expensemanager

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.expensemanager.AuthFragments.LoginFragment
import com.example.expensemanager.ViewModels.MainViewModel
import com.example.expensemanager.ViewModels.MainViewModelFactory
import com.example.expensemanager.databinding.ActivityMainBinding
import com.example.expensemanager.repository.Repository

class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setting up api confi
        val viewModelFactory= MainViewModelFactory(Repository())
        viewModel=ViewModelProvider(this,viewModelFactory) [MainViewModel::class.java]

        val sharedPref=this?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)

        if(sharedPref?.getBoolean("success",false) == true){
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_Container,LoginFragment()).commit()
        }
    }




}