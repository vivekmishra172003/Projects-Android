package com.example.internetcheck2nov2024

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var noInternetLayout: View
    private lateinit var tvHelloWorld: TextView
    private lateinit var btnRetry: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize views
        noInternetLayout = findViewById(R.id.noInternetLayout)
        tvHelloWorld = findViewById(R.id.tvHelloWorld)
        btnRetry = findViewById(R.id.btnRetry)

        // Set up retry button click listener
        btnRetry.setOnClickListener {
            checkInternetConnection()
        }

        // Initial internet check
        checkInternetConnection()

        // Set up edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkInternetConnection() {
        if (NetworkUtils.isInternetAvailable(this)) {
            noInternetLayout.visibility = View.GONE
            tvHelloWorld.visibility = View.VISIBLE
        } else {
            noInternetLayout.visibility = View.VISIBLE
            tvHelloWorld.visibility = View.GONE
        }
    }
}