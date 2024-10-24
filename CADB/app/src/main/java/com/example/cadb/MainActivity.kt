package com.example.cadb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import androidx.activity.viewModels

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.cadb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private var imageUri: Uri? = null
    private var availableRooms: Int = 100 // Initialize available rooms to 100

    // Register for activity to select an image
    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUri = result.data?.data
                binding.imageViewProfile.setImageURI(imageUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Display initial available rooms
        binding.textViewRoomsAvailable.text = "Rooms Available: $availableRooms"

        // Set up image picker on button click
        binding.buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            selectImageLauncher.launch(intent)
        }

        // Register user (book room)
        binding.buttonRegister.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val password = binding.editTextPassword.text.toString()
            val mobile = binding.editTextMobile.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty() && mobile.isNotEmpty() && imageUri != null) {
                if (availableRooms > 0) {
                    val user = User(imageUri = imageUri.toString(), name = name, password = password, mobile = mobile)
                    userViewModel.insertUser(user)

                    // Decrease the available rooms by 1
                    availableRooms -= 1
                    binding.textViewRoomsAvailable.text = "Rooms Available: $availableRooms"

                    Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show()

                    // Clear input fields
                    binding.editTextName.text.clear()
                    binding.editTextPassword.text.clear()
                    binding.editTextMobile.text.clear()
                    binding.imageViewProfile.setImageURI(null)
                    imageUri = null
                } else {
                    Toast.makeText(this, "No rooms available!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields and select an image!", Toast.LENGTH_SHORT).show()
            }
        }

        // Show remaining rooms on login button click
        binding.buttonLogin.setOnClickListener {
            Toast.makeText(this, "Rooms Available: $availableRooms", Toast.LENGTH_SHORT).show()
        }
    }
}
