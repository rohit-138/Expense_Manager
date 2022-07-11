package com.example.expensemanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensemanager.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding:FragmentHomeBinding?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogout=binding?.btnLogout
        val sharedPref=this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val editor=sharedPref?.edit()

//        btnLogout?.setOnClickListener {
//            editor?.clear()
//            editor?.commit()
//            replaceFragment(LoginFragment())
//
//        }





    }

    private fun replaceFragment(fragment:Fragment){
        val fragmetManager= parentFragmentManager
        val fragmentTransaction=fragmetManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_Container,fragment)
        fragmentTransaction.commit()
    }


}