package com.example.junoassessment.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.junoassessment.data.model.YourCryptoHolding
import com.example.junoassessment.databinding.CryptoHoldingsListBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CryptoHoldingAdapter : RecyclerView.Adapter<CryptoHoldingAdapter.CryptoHoldingViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<YourCryptoHolding>() {
        override fun areItemsTheSame(
            oldItem: YourCryptoHolding,
            newItem: YourCryptoHolding
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: YourCryptoHolding,
            newItem: YourCryptoHolding
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHoldingViewHolder {
        val binding = CryptoHoldingsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoHoldingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHoldingViewHolder, position: Int) {
        return holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class CryptoHoldingViewHolder(
        val binding: CryptoHoldingsListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(yourCryptoHolding: YourCryptoHolding) {
            binding.cryptoHoldingValueLayout.visibility = View.GONE
            binding.cryptoHoldingEmptyLayout.visibility = View.VISIBLE
            binding.title.text = yourCryptoHolding.title
            GlideToVectorYou
                .init()
                .with(binding.logo.context)
                .load(yourCryptoHolding.logo?.toUri(), binding.logo)

            binding.buyBtn.setOnClickListener {
                Toast.makeText(binding.buyBtn.context, "Buy button clicked", Toast.LENGTH_LONG).show()
            }

            binding.depositBtn.setOnClickListener {
                Toast.makeText(binding.depositBtn.context, "Deposit button clicked", Toast.LENGTH_LONG).show()
            }
        }
    }

}