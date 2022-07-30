package com.example.expensemanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensemanager.AuthFragments.LoginFragment
import com.example.expensemanager.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

private var binding:FragmentAccountBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAccountBinding.inflate(inflater,container,false)

        val btnLogout=binding?.btnLogout
        val sharedPref=this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()
        val loginFragment= LoginFragment()

        btnLogout?.setOnClickListener {
            editor?.clear()
            editor?.commit()
            replaceFragment(loginFragment)
        }

        return binding?.root
    }
    private fun replaceFragment(fragment:Fragment){
        val fragmetManager= parentFragmentManager
        val fragmentTransaction=fragmetManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_Container,fragment)
        fragmentTransaction.commit()
    }


}