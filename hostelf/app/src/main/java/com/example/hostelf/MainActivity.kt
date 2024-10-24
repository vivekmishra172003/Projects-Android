package com.example.hostelf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.cadb.R


class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private var imageUri: Uri? = null

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
        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextMobile: EditText = findViewById(R.id.editTextMobile)
        val editTextDob: EditText = findViewById(R.id.editTextDob)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        // Set up image picker
        buttonSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            selectImageLauncher.launch(intent)
        }

        // Register user (book room)
        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString()
            val mobile = editTextMobile.text.toString()
            val dob = editTextDob.text.toString()
            val password = editTextPassword.text.toString()

            if (name.isNotEmpty() && mobile.isNotEmpty() && dob.isNotEmpty() && password.isNotEmpty() && imageUri != null) {
                val user = User(imageUri = imageUri.toString(), name = name, mobile = mobile, dob = dob, password = password)
                userViewModel.insertUser(user)

                Toast.makeText(this, "Room booked successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields and select an image!", Toast.LENGTH_SHORT).show()
            }
        }

        // Login user and show available rooms
        buttonLogin.setOnClickListener {
            val mobile = editTextMobile.text.toString()
            val password = editTextPassword.text.toString()

            if (mobile.isNotEmpty() && password.isNotEmpty()) {
                userViewModel.login(mobile, password).observe(this) { user ->
                    if (user != null) {
                        val intent = Intent(this, RemainingRoomsActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter mobile and password!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
