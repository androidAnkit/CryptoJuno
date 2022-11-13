package com.example.junoassessment.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junoassessment.R
import com.example.junoassessment.data.util.Resource
import com.example.junoassessment.databinding.ActivityValueStateBinding
import com.example.junoassessment.presentation.adapter.CryptoHoldingAdapter
import com.example.junoassessment.presentation.adapter.CryptoPriceAdapter
import com.example.junoassessment.presentation.adapter.TransactionAdapter
import com.example.junoassessment.presentation.viewModel.ValueStateViewModel
import com.example.junoassessment.presentation.viewModel.ValueStateViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ValueStateActivity : AppCompatActivity() {

    @Inject
    lateinit var valueStateViewModelFactory: ValueStateViewModelFactory

    @Inject
    lateinit var cryptoHoldingAdapter: CryptoHoldingAdapter

    @Inject
    lateinit var cryptoPriceAdapter: CryptoPriceAdapter

    @Inject
    lateinit var transactionAdapter: TransactionAdapter

    lateinit var valueStateViewModel: ValueStateViewModel
    private var isLoading = false
    lateinit var binding: ActivityValueStateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityValueStateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        valueStateViewModel = ViewModelProvider(this, valueStateViewModelFactory)
            .get(ValueStateViewModel::class.java)

        // Setting the list on your crypto holding adapter
        initCHRecyclerView()
        // Setting the list on All transactions adapter
        initTRecyclerView()
        // Setting the list on cost price adapter
        initCPRecyclerView()

        // View your crypto holdings
        viewValueStateData()

    }

    fun viewValueStateData() {
        valueStateViewModel.getValueUseCaseData()

        valueStateViewModel.valueStateData.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        // Updating the CryptoHolding List
                        cryptoHoldingAdapter.differ.submitList(it?.yourCryptoHoldings.toList())
                        // Updating the Transaction List
                        transactionAdapter.differ.submitList(it?.allTransactions.toList())
                        // Updating the Price List
                        cryptoPriceAdapter.differ.submitList(it?.cryptoPrices.toList())
                        binding.cryptoValueAccount.text = it.cryptoBalance.title
                        binding.cryptoValueSH.text = it.cryptoBalance.subtitle
                        binding.cryptoValuePrice.text = resources.getString(R.string.dollar) +
                                it.cryptoBalance.currentBalInUsd
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(
                        this,
                        "An error occured: ${it.message}, code: ${it.code}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else ->
                    finishAndRemoveTask()
            }
        })
    }

    fun initCHRecyclerView() {
        binding.rvValueCryptoHoldings.apply {
            adapter = cryptoHoldingAdapter
            layoutManager = LinearLayoutManager(this@ValueStateActivity)
        }
    }

    fun initTRecyclerView() {
        binding.rvValueRecentTransactions.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(this@ValueStateActivity)
        }
    }

    fun initCPRecyclerView() {
        binding.rvValueCostPrice.apply {
            adapter = cryptoPriceAdapter
            layoutManager =
                LinearLayoutManager(this@ValueStateActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressValueBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressValueBar.visibility = View.INVISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAndRemoveTask()
    }

    override fun onDestroy() {
        super.onDestroy()
        transactionAdapter.differ.submitList(null)
        cryptoPriceAdapter.differ.submitList(null)
        cryptoHoldingAdapter.differ.submitList(null)
    }
}