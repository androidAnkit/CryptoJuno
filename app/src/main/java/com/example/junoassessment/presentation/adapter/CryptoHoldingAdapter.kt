package com.example.junoassessment.presentation.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.junoassessment.R
import com.example.junoassessment.data.model.YourCryptoHolding
import com.example.junoassessment.data.util.Constants
import com.example.junoassessment.databinding.CryptoHoldingsListBinding
import com.example.junoassessment.presentation.activity.BuyActivity
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CryptoHoldingAdapter :
    RecyclerView.Adapter<CryptoHoldingAdapter.CryptoHoldingViewHolder>() {

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
            // Hiding the last extra line
            if (adapterPosition.equals(differ.currentList.size - 1)) {
                binding.cryptoHoldingLine.visibility = View.INVISIBLE
            } else {
                binding.cryptoHoldingLine.visibility = View.VISIBLE
            }

            // Check to open the layout for the Empty Activity
            if (yourCryptoHolding.currentBalInUsd.equals("0.00")) {
                binding.cryptoHoldingValueLayout.visibility = View.GONE
                binding.cryptoHoldingEmptyLayout.visibility = View.VISIBLE
                binding.title.text = yourCryptoHolding.title
                GlideToVectorYou
                    .init()
                    .with(binding.logo.context)
                    .load(yourCryptoHolding.logo?.toUri(), binding.logo)

                // Opening the buy activity
                binding.buyBtn.setOnClickListener {
                    val buyIntent = Intent(binding.buyBtn.context, BuyActivity::class.java)
                    binding.buyBtn.context.startActivity(buyIntent)
                }

                // On click of deposit button
                binding.depositBtn.setOnClickListener {
                    Toast.makeText(
                        binding.depositBtn.context,
                        "Deposit button clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {    // Setting layout for Value Activity
                binding.cryptoHoldingEmptyLayout.visibility = View.GONE
                binding.cryptoHoldingValueLayout.visibility = View.VISIBLE
                binding.cryptoName.text = yourCryptoHolding.title
                binding.cryptoQuantity.text =
                    yourCryptoHolding.currentBalInToken + " " + yourCryptoHolding.title
                binding.cryptoPrice.text =
                    binding.cryptoPrice.context.resources.getString(R.string.dollar) +
                            yourCryptoHolding.currentBalInUsd
                GlideToVectorYou
                    .init()
                    .with(binding.cryptoLogo.context)
                    .load(yourCryptoHolding.logo?.toUri(), binding.cryptoLogo)
                when (yourCryptoHolding.title) {
                    Constants.BTC2 ->
                        binding.cryptoGain.text =
                            binding.cryptoGain.context.resources.getString(R.string.chGainsBtc)
                    Constants.ETH2 ->
                        binding.cryptoGain.text =
                            binding.cryptoGain.context.resources.getString(R.string.chGainsEth)
                    Constants.USDC2, Constants.AVAX2 ->
                        binding.cryptoGain.text =
                            binding.cryptoGain.context.resources.getString(R.string.chGainsUSAV)
                    else -> {
                        binding.cryptoGain.text =
                            binding.cryptoGain.context.resources.getString(R.string.chGains)
                    }
                }
            }
        }
    }
}