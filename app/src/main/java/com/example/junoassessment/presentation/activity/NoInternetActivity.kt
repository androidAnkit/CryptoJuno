package com.example.junoassessment.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.junoassessment.data.util.Constants
import com.example.junoassessment.databinding.ActivityNoInternetBinding
import com.example.junoassessment.presentation.viewModel.EmptyStateViewModel
import com.example.junoassessment.presentation.viewModel.NoInternetViewModel
import com.example.junoassessment.presentation.viewModel.NoInternetViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@AndroidEntryPoint
class NoInternetActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: NoInternetViewModelFactory
    lateinit var viewModel: NoInternetViewModel
    lateinit var binding: ActivityNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(NoInternetViewModel::class.java)
        val isFromEmptyState = intent.getBooleanExtra(Constants.EMPTY_STATE, false)
        val isFromValueState = intent.getBooleanExtra(Constants.VALUE_STATE, false)
        val isFromBuy = intent.getBooleanExtra(Constants.BUY_STATE, false)
        binding.retryBtn.setOnClickListener {
            // Check for internet
            if (viewModel.isNetworkAvailable()) {
                // Open Empty Activity
                if (isFromEmptyState) {
                    val openEmptyState =
                        Intent(this@NoInternetActivity, EmptyStateActivity::class.java)
                    startActivity(openEmptyState)
                    finishAndRemoveTask()
                    return@setOnClickListener
                }

                // Open Value Activity
                if (isFromValueState) {
                    val openValueState =
                        Intent(this@NoInternetActivity, ValueStateActivity::class.java)
                    startActivity(openValueState)
                    finishAndRemoveTask()
                    return@setOnClickListener
                }

                // Open Buy Activity
                if (isFromBuy) {
                    val openValueState =
                        Intent(this@NoInternetActivity, BuyActivity::class.java)
                    startActivity(openValueState)
                    finishAndRemoveTask()
                    return@setOnClickListener
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAndRemoveTask()
    }
}
