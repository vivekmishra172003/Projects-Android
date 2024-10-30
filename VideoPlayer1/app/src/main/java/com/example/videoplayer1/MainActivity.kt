package com.example.videoplayer1

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set padding for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize VideoView
        val videoView: VideoView = findViewById(R.id.videoView)

        // Load the video from res/raw and play it
        playVideoFromRaw(videoView)
    }

    private fun playVideoFromRaw(videoView: VideoView) {
        // Load video from res/raw using Uri
        val videoUri = Uri.parse("android.resource://$packageName/${R.raw.sample_video}")

        // Set the URI to VideoView
        videoView.setVideoURI(videoUri)

        // Start playing the video automatically
        videoView.start()
    }
}
