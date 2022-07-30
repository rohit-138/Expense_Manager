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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.ViewModels.TransactionViewModel
import com.example.expensemanager.ViewModels.TransactionViewModelFactory
import com.example.expensemanager.databinding.FragmentHomeBinding
import com.example.expensemanager.models.getMontlyIncomeAndExpenseData
import com.example.expensemanager.models.getTransactionData
import com.example.expensemanager.repository.Repository
import java.util.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var transactionViewModel: TransactionViewModel
    private val month = (Calendar.getInstance().get(Calendar.MONTH) + 1)

    private val myAdapter by lazy {MyAdapter()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val sharedPref = this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val authToken = sharedPref?.getString("token", "").toString()
        val userName = sharedPref?.getString("name", "")

        binding?.usernameTextView?.text=userName

        val repository = Repository()
        val transactionViewModelFactory = TransactionViewModelFactory(repository)
        transactionViewModel =
            ViewModelProvider(this, transactionViewModelFactory)[TransactionViewModel::class.java]

        transactionViewModel.getMontlyIncomeAndExpense(
            getMontlyIncomeAndExpenseData(month),
            authToken
        )

        transactionViewModel.mygetMontlyIncomeAndExpense.observe(
            viewLifecycleOwner,
            Observer { response ->
                if (response.isSuccessful) {

                    Log.d("Main", response.toString())
                    binding?.incomeTextView?.text = "Rs ${response.body()?.income.toString()}"
                    binding?.expenseTextView?.text = "Rs ${response.body()?.expense.toString()}"
                    Log.d("Main", response.body().toString())
                } else {
                    Toast.makeText(
                        activity,
                        "Failed to fetch monthly expenditure",
                        Toast.LENGTH_LONG
                    ).show()

                }
            })
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = this.activity?.getSharedPreferences("loginTokens", Context.MODE_PRIVATE)
        val authToken = sharedPref?.getString("token", "").toString()
        setUpRecycleView()
            transactionViewModel.getMontlyTransactions(getTransactionData(month),authToken)
        transactionViewModel.myGetMontlyTransaction.observe(
            viewLifecycleOwner,
            Observer{response->
                if(response.isSuccessful){
                    Log.d("Main",response.toString())
                    Log.d("Main",response.body().toString())
                    response?.body()?.let { myAdapter.setData(it) }
                }else{
                    Log.d("Main",response.body().toString())
                    Log.d("Main",response.toString())
                    Toast.makeText(activity,"response of this post request not received",Toast.LENGTH_LONG).show()
                }
            }
        )

//
    }
    private fun setUpRecycleView(){
        binding?.transactionRecyclerView?.adapter=myAdapter
        binding?.transactionRecyclerView?.layoutManager= LinearLayoutManager(context)
    }
}