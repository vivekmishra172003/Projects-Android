package com.example.finalproject


import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private lateinit var btnPlayPause: Button
    private lateinit var btnBack: Button
    private var videoResourceId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        // Get video resource ID from intent
        videoResourceId = intent.getIntExtra("VIDEO_RESOURCE_ID", 0)

        // Initialize views
        videoView = findViewById(R.id.videoView)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnBack = findViewById(R.id.btnBack)

        // Load and play video
        playVideo()

        // Set play/pause button click listener
        btnPlayPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            } else {
                videoView.start()
            }
        }

        // Set back button click listener
        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Set video completion listener
        videoView.setOnCompletionListener {
            // Optional: loop the video or show a message
            videoView.resume()
        }
    }

    private fun playVideo() {
        // Load video from res/raw using Uri
        val videoUri = Uri.parse("android.resource://$packageName/$videoResourceId")

        // Set the URI to VideoView
        videoView.setVideoURI(videoUri)

        // Start playing the video automatically
        videoView.start()
    }
}