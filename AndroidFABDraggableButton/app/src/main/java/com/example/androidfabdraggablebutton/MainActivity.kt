package com.example.androidfabdraggablebutton

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var xDelta = 0.0f
    private var yDelta = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fab.setOnTouchListener { view, event ->
            // Set an OnTouchListener for the FloatingActionButton (FAB)
            when (event.action) { // Check the type of touch event
                MotionEvent.ACTION_DOWN -> {
                    // If the event is ACTION_DOWN (finger touches the screen)
                    xDelta = view.x - event.rawX
                    // Calculate the difference between the FAB's x position and the touch's x position
                    yDelta = view.y - event.rawY
                    // Calculate the difference between the FAB's y position and the touch's y position
                    true // Consume the touch event (prevent other views from handling it)
                }
                MotionEvent.ACTION_MOVE -> {
                    // If the event is ACTION_MOVE (finger moves on the screen)
                    view.animate()
                        // Start an animation for the FAB
                        .x(event.rawX + xDelta)
                        // Set the new x position of the FAB (touch position + initial difference)
                        .y(event.rawY + yDelta)
                        // Set the new y position of the FAB (touch position + initial difference)
                        .setDuration(0)
                        // Set the animation duration to 0 (move instantly)
                        .start()
                    // Start the animation
                    true // Consume the touch event (prevent other views from handling it)
                }
                else -> true // Consume other touch events (e.g., ACTION_UP, ACTION_CANCEL)
            }
        }
    }
}