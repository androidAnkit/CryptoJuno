package com.example.junoassessment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.junoassessment.data.model.AllTransaction
import com.example.junoassessment.databinding.AllTransactionListBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<AllTransaction>() {
        override fun areItemsTheSame(
            oldItem: AllTransaction,
            newItem: AllTransaction
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: AllTransaction,
            newItem: AllTransaction
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = AllTransactionListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class TransactionViewHolder(
        val binding: AllTransactionListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(allTransaction: AllTransaction) {
            if(allTransaction!=null){
                binding.firstFieldNearImg.text = allTransaction.title
                binding.secFieldNearImage.text = allTransaction.txnTime
                binding.firstFieldNearEnd.text = allTransaction.txnAmount
                binding.secFieldNearEnd.text = allTransaction.txnSubAmount
                GlideToVectorYou
                    .init()
                    .with(binding.cryptoImg.context)
                    .load(allTransaction.txnLogo?.toUri(), binding.cryptoImg)
            }else{
                TODO("Need to add the update check")
            }
        }
    }
}