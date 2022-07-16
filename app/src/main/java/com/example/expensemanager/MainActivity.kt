package com.example.expensemanager

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
        //setting up api config
        var repository=Repository()
        val viewModelFactory= MainViewModelFactory(repository)
        viewModel=ViewModelProvider(this,viewModelFactory) [MainViewModel::class.java]

    var loginFragment= LoginFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_Container,loginFragment)
        fragmentTransaction.commit()
    }


}