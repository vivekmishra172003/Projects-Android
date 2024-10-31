package com.example.finalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView

class PDFViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdfviewer)

        val pdfFileName = intent.getStringExtra("PDF_NAME") ?: "cc1.pdf"

        val pdfView = findViewById<PDFView>(R.id.pdfView)
        try {
            pdfView.fromAsset(pdfFileName)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .load()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}