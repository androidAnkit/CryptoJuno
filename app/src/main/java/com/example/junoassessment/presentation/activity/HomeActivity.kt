package com.example.junoassessment.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.junoassessment.R
import com.example.junoassessment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Open Empty Activity
        binding.emptyState.setOnClickListener {
            val emptyStateIntent = Intent(this@HomeActivity, EmptyStateActivity::class.java)
            startActivity(emptyStateIntent)
        }

        // Open Value Activity
        binding.valueState.setOnClickListener {
            val valueStateIntent = Intent(this@HomeActivity, ValueStateActivity::class.java)
            startActivity(valueStateIntent)
        }

    }
}