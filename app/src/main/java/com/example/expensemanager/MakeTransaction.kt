package com.example.expensemanager

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensemanager.ViewModels.TransactionViewModel
import com.example.expensemanager.ViewModels.TransactionViewModelFactory
import com.example.expensemanager.databinding.FragmentMakeTransactionBinding
import com.example.expensemanager.models.TransactionData
import com.example.expensemanager.repository.Repository
import java.lang.Integer.parseInt
import java.util.*


class MakeTransaction : Fragment() {

    private var binding: FragmentMakeTransactionBinding? = null
    private lateinit var viewmodel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMakeTransactionBinding.inflate(inflater, container, false)

        val repository = Repository()
        val viewModelFactory = TransactionViewModelFactory(repository)
        viewmodel = ViewModelProvider(this, viewModelFactory)[TransactionViewModel::class.java]

//        val transactionData=TransactionData("income",100)
        binding?.dateToday?.text = getTodaysDate()
        val sharedPref = this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)

        val transactionTypes = resources.getStringArray(R.array.transaction_type)

        val adapterTransaction =
            ArrayAdapter(requireContext(), R.layout.dropdown_items, transactionTypes)

        binding?.autoCompleteTextView?.setAdapter(adapterTransaction)

        val transactionType = binding?.transactionType

        var typeOfTransaction: String? = null

        (transactionType?.getEditText() as AutoCompleteTextView).onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, _ ->
                // this code block is called every time an item is clicked
                typeOfTransaction = adapterTransaction.getItem(position)
            }

        binding?.submit?.setOnClickListener {
            val transactionType = typeOfTransaction.toString().lowercase()
            val amount = parseInt(binding?.amount?.text.toString())
            val note = binding?.noteForTransaction?.text.toString()
            val month = (Calendar.getInstance().get(Calendar.MONTH)+1)

            val authToken = sharedPref?.getString("token", "").toString()

            val transactionData = TransactionData(transactionType, amount, month, note)
            viewmodel.addTransaction(transactionData, authToken)
            viewmodel.myAddTransactionResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    findNavController().navigate(R.id.action_makeTransaction_to_homeFragment)
                    Log.d("Main", "this is " + response.body().toString())
                } else {
                    Log.d("Main", "transaction not added $response")
                    Log.d("Main", "transactionData is $transactionData")
                }
            })
        }






        return binding?.root
    }

    private fun getTodaysDate(): String {
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)+1
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$day"
    }


}