package com.example.finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cal : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var number1EditText: EditText
    private lateinit var number2EditText: EditText
    private lateinit var addButton: Button
    private lateinit var subtractButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var divideButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)

        // Set up window insets to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI elements
        resultTextView = findViewById(R.id.resultTextView)
        number1EditText = findViewById(R.id.number1EditText)
        number2EditText = findViewById(R.id.number2EditText)
        addButton = findViewById(R.id.addButton)
        subtractButton = findViewById(R.id.subtractButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        divideButton = findViewById(R.id.divideButton)

        // Set up button click listeners
        addButton.setOnClickListener { performCalculation(::add) }
        subtractButton.setOnClickListener { performCalculation(::subtract) }
        multiplyButton.setOnClickListener { performCalculation(::multiply) }
        divideButton.setOnClickListener { performCalculation(::divide) }
    }

    private fun performCalculation(operation: (Double, Double) -> Double) {
        val num1 = number1EditText.text.toString().toDoubleOrNull() ?: 0.0
        val num2 = number2EditText.text.toString().toDoubleOrNull() ?: 0.0
        val result = operation(num1, num2)
        resultTextView.text = result.toString()
    }

    private fun add(a: Double, b: Double) = a + b
    private fun subtract(a: Double, b: Double) = a - b
    private fun multiply(a: Double, b: Double) = a * b
    private fun divide(a: Double, b: Double) = if (b != 0.0) a / b else 0.0
}