package com.example.hostelf

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cadb.R

class RemainingRoomsActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remaining_rooms)

        val textViewRemainingRooms: TextView = findViewById(R.id.textViewRemainingRooms)


        userViewModel.getAvailableRooms().observe(this) { availableRooms ->
            textViewRemainingRooms.text = "Available Rooms: $availableRooms"
        }
    }
}
