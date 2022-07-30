package com.example.expensemanager

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.models.getTransactionResponse
import com.example.expensemanager.databinding.TransactionItemListBinding
import com.example.expensemanager.models.AllTransactionResponse
import com.example.expensemanager.models.TransactionX

class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var mylist = emptyList<TransactionX>()
    inner class MyViewHolder(var binding:TransactionItemListBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TransactionItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.Type.text=mylist[position].type

        if(mylist[position].type=="income"){
            Log.d("Main","in onBindViewHolder green ${mylist[position].type}")
            holder.binding.tamount.setTextColor(Color.parseColor("#37953B"))
        }
        else{

            holder.binding.tamount.setTextColor(Color.parseColor("#E91E63"))
            Log.d("Main","in onBindViewHolder green")
        }
        holder.binding.tamount.text="Rs ${mylist[position].amount.toString()}"
        holder.binding.tnote.text=mylist[position].note.toString()
        var clicked:Boolean=false
//        if (clicked==false){
//            holder.binding.transactionCardView.setOnClickListener{
//                holder.binding.transactionCardView.setBackgroundColor(Color.parseColor("#8F767E"))
//                clicked=true
//                Log.d("Main","Changed color")
//            }
//        }else{
//            holder.binding.transactionCardView.setOnClickListener{
//                holder.binding.transactionCardView.setBackgroundColor(Color.parseColor("#EDE7E9"))
//                clicked=false
//                Log.d("Main","Changed color")
//            }
//        }
    }
    override fun getItemCount(): Int {
        return mylist.size
    }
    fun setData(newList: List<TransactionX>){
        mylist=newList
        notifyDataSetChanged()
    }
}