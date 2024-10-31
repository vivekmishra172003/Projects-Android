package com.example.finalproject


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class VideoSelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_selector)

        // Find all video selector buttons
        val btnVideo1: Button = findViewById(R.id.btnVideo1)
        val btnVideo2: Button = findViewById(R.id.btnVideo2)
        val btnVideo3: Button = findViewById(R.id.btnVideo3)
        val btnVideo4: Button = findViewById(R.id.btnVideo4)

        // Set click listeners for each video button
        btnVideo1.setOnClickListener { openVideoPlayer(R.raw.v1) }
        btnVideo2.setOnClickListener { openVideoPlayer(R.raw.v2) }
        btnVideo3.setOnClickListener { openVideoPlayer(R.raw.v3) }
        btnVideo4.setOnClickListener { openVideoPlayer(R.raw.v4) }
    }

    private fun openVideoPlayer(videoResourceId: Int) {
        val intent = Intent(this, VideoPlayerActivity::class.java).apply {
            putExtra("VIDEO_RESOURCE_ID", videoResourceId)
        }
        startActivity(intent)
    }
}