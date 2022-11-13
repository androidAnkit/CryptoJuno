package com.example.junoassessment.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.junoassessment.data.util.Constants
import com.example.junoassessment.databinding.ActivityBuyBinding
import com.example.junoassessment.presentation.viewModel.BuyViewModel
import com.example.junoassessment.presentation.viewModel.BuyViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BuyActivity : AppCompatActivity() {
    @Inject
    lateinit var buyViewModelFactory: BuyViewModelFactory
    lateinit var buyViewModel: BuyViewModel
    lateinit var binding: ActivityBuyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buyViewModel = ViewModelProvider(this, buyViewModelFactory)
            .get(BuyViewModel::class.java)

        // Check for internet
        if (buyViewModel.isNetworkAvailable()) {
            binding.successLayout.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE

            Handler(Looper.getMainLooper()).postDelayed({
                binding.successLayout.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
            }, 2 * 1000)

            // On click of close btn
            binding.closeBtn.setOnClickListener {
                finishAndRemoveTask()
            }
        } else {
            val noInternet = Intent(this@BuyActivity, NoInternetActivity::class.java)
            noInternet.putExtra(Constants.BUY_STATE, true)
            noInternet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(noInternet)
            finishAndRemoveTask()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAndRemoveTask()
    }
}
