package com.example.hostel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private var imageUri: Uri? = null
    private var availableRooms: Int = 100 // Initialize available rooms to 100

    // Register for activity to select an image
    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                imageUri = result.data?.data
                findViewById<ImageView>(R.id.imageViewProfile).setImageURI(imageUri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSelectImage: Button = findViewById(R.id.buttonSelectImage)
        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val textViewRoomsAvailable: TextView = findViewById(R.id.textViewRoomsAvailable)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val editTextMobile: EditText = findViewById(R.id.editTextMobile)
        val imageViewProfile: ImageView = findViewById(R.id.imageViewProfile)

        // Display initial available rooms
        textViewRoomsAvailable.text = "Rooms Available: $availableRooms"

        // Set up image picker on button click
        buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            selectImageLauncher.launch(intent)
        }

        // Register user (book room)
        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString()
            val password = editTextPassword.text.toString()
            val mobile = editTextMobile.text.toString()

            if (name.isNotEmpty() && password.isNotEmpty() && mobile.isNotEmpty() && imageUri != null) {
                if (availableRooms > 0) {
                    val user = User(imageUri = imageUri.toString(), name = name, password = password, mobile = mobile)
                    userViewModel.insertUser(user)

                    // Decrease the available rooms by 1
                    availableRooms -= 1
                    textViewRoomsAvailable.text = "Rooms Available: $availableRooms"

                    Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show()

                    // Clear input fields
                    editTextName.text.clear()
                    editTextPassword.text.clear()
                    editTextMobile.text.clear()
                    imageViewProfile.setImageURI(null)
                    imageUri = null
                } else {
                    Toast.makeText(this, "No rooms available!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields and select an image!", Toast.LENGTH_SHORT).show()
            }
        }

        // Show remaining rooms on login button click
        buttonLogin.setOnClickListener {
            Toast.makeText(this, "Rooms Available: $availableRooms", Toast.LENGTH_SHORT).show()
        }
    }
}
