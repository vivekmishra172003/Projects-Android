package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class Fourpdf : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourpdf)

        // Initialize buttons
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)

        // Set click listeners for each button
        button1.setOnClickListener { openPDF("cc1.pdf") }
        button2.setOnClickListener { openPDF("cc2.pdf") }
        button3.setOnClickListener { openPDF("cc3.pdf") }
        button4.setOnClickListener { openPDF("cc4.pdf") }
    }

    private fun openPDF(pdfFileName: String) {
        val intent = Intent(this, PDFViewerActivity::class.java).apply {
            putExtra("PDF_NAME", pdfFileName)
        }
        startActivity(intent)
    }
}