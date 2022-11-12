package com.example.junoassessment.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.junoassessment.R
import com.example.junoassessment.data.Constants
import com.example.junoassessment.data.model.CryptoPrice
import com.example.junoassessment.data.model.YourCryptoHolding
import com.example.junoassessment.databinding.AllTransactionListBinding
import com.example.junoassessment.databinding.CurrentPricesListBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CryptoPriceAdapter : RecyclerView.Adapter<CryptoPriceAdapter.CryptoPriceViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<CryptoPrice>() {
        override fun areItemsTheSame(
            oldItem: CryptoPrice,
            newItem: CryptoPrice
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: CryptoPrice,
            newItem: CryptoPrice
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoPriceViewHolder {
        val binding =
            CurrentPricesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoPriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoPriceViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class CryptoPriceViewHolder(
        val binding: CurrentPricesListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoPrice: CryptoPrice) {

            binding.cryptoName.text = cryptoPrice.title
            binding.cryptoPrice.text = cryptoPrice.currentPriceInUsd
            GlideToVectorYou
                .init()
                .with(binding.cpLogo.context)
                .load(cryptoPrice.logo?.toUri(), binding.cpLogo)

            when (cryptoPrice.title) {
                Constants.BTC, Constants.USDC -> {
                    binding.profitLossImg.setImageResource(R.drawable.ic_trending_down)
                    binding.cryptoProfitLossPercentage.setTextColor(
                        ContextCompat.getColor(
                            binding.cryptoProfitLossPercentage.context,
                            R.color.red
                        )
                    )
                }

                Constants.ETH, Constants.AVAX -> {
                    binding.profitLossImg.setImageResource(R.drawable.ic_trending_up)
                    binding.cryptoProfitLossPercentage.setTextColor(
                        ContextCompat.getColor(
                            binding.cryptoProfitLossPercentage.context,
                            R.color.green
                        )
                    )
                }

                else -> {
                    binding.profitLossImg.setImageResource(R.drawable.ic_trending_down)
                    binding.cryptoProfitLossPercentage.setTextColor(
                        ContextCompat.getColor(
                            binding.cryptoProfitLossPercentage.context,
                            R.color.red
                        )
                    )
                }
            }
        }
    }
}