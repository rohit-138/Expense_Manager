package com.example.expensemanager

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.expensemanager.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private var binding: ActivityHomeBinding? = null
    private var bottomNavigationview: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val sharedPref = this.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        bottomNavigationview = binding?.bottomNavigationView

//        val navController by lazy {
//            val navHostFragment = supportFragmentManager
//                .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//            navHostFragment.navController
//    }
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController=navHostFragment.navController
        bottomNavigationview?.setupWithNavController(navController)

        binding?.floatingAdd?.setOnClickListener {

            navController.navigate(R.id.homeFragment)
            navController.navigate(R.id.action_homeFragment_to_makeTransaction)
        }
        bottomNavigationview?.setOnItemSelectedListener {
        if(it.itemId==R.id.exit) {
            editor?.clear()?.commit()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }else if (it.itemId==R.id.homeFragment){
            navController.navigate(R.id.homeFragment)
        }
        else if (it.itemId==R.id.accountFragment){
            navController.navigate(R.id.accountFragment)
        }
        else if (it.itemId==R.id.helpFragment){
            navController.navigate(R.id.helpFragment)
        }
            true
        }
    }
}