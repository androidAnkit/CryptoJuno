package com.example.junoassessment.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junoassessment.data.util.Resource
import com.example.junoassessment.databinding.ActivityEmptyStateBinding
import com.example.junoassessment.presentation.adapter.CryptoHoldingAdapter
import com.example.junoassessment.presentation.adapter.CryptoPriceAdapter
import com.example.junoassessment.presentation.adapter.TransactionAdapter
import com.example.junoassessment.presentation.viewModel.EmptyStateViewModel
import com.example.junoassessment.presentation.viewModel.EmptyStateViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmptyStateActivity : AppCompatActivity() {

    @Inject
    lateinit var emptyStateViewModelFactory: EmptyStateViewModelFactory

    @Inject
    lateinit var cryptoHoldingAdapter: CryptoHoldingAdapter

    @Inject
    lateinit var cryptoPriceAdapter: CryptoPriceAdapter

    @Inject
    lateinit var transactionAdapter: TransactionAdapter

    lateinit var binding: ActivityEmptyStateBinding
    lateinit var emptyViewModel: EmptyStateViewModel
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyStateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emptyViewModel = ViewModelProvider(this, emptyStateViewModelFactory)
            .get(EmptyStateViewModel::class.java)

        // Setting the list on your crypto holding adapter
        initCHRecyclerView()
        // Setting the list on All transactions adapter
        initTRecyclerView()
        // Setting the list on cost price adapter
        initCPRecyclerView()

        // View your crypto holdings
        viewEmptyStateData()

    }

    fun viewEmptyStateData() {
        emptyViewModel.getEmptyUseCaseData()

        emptyViewModel.emptyStateData.observe(this, Observer {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                        cryptoHoldingAdapter.differ.submitList(it?.yourCryptoHoldings.toList())
                        transactionAdapter.differ.submitList(it?.allTransactions.toList())
                        cryptoPriceAdapter.differ.submitList(it?.cryptoPrices.toList())
                        if (transactionAdapter.differ.currentList.isEmpty()) {
                            binding.rvRecentTransactions.visibility = View.INVISIBLE
                            binding.noData.visibility = View.VISIBLE
                        }else{
                            binding.noData.visibility = View.INVISIBLE
                            binding.rvRecentTransactions.visibility = View.VISIBLE
                        }
                        binding.cryptoAccount.text = it.cryptoBalance.title
                        binding.cryptoSH.text = it.cryptoBalance.subtitle
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

                else -> {
                    Log.i("MYTAG", "Inside else condition")
                    finishAndRemoveTask()
                }

            }
        })
    }

    fun initCHRecyclerView() {
        binding.rvCryptoHoldings.apply {
            adapter = cryptoHoldingAdapter
            layoutManager = LinearLayoutManager(this@EmptyStateActivity)
        }
    }

    fun initTRecyclerView() {
        binding.rvRecentTransactions.apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(this@EmptyStateActivity)
        }
    }

    fun initCPRecyclerView() {
        binding.rvCostPrice.apply {
            adapter = cryptoPriceAdapter
            layoutManager = LinearLayoutManager(
                this@EmptyStateActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
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