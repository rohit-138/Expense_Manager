package com.example.expensemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensemanager.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private  var binding:FragmentRegisterBinding? = null



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


}