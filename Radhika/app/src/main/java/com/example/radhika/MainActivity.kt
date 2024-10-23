package com.example.radhika

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.cadb.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registrationNo = findViewById<EditText>(R.id.et_registration_no)
        val name = findViewById<EditText>(R.id.et_name)
        val mobile = findViewById<EditText>(R.id.et_mobile)
        val password = findViewById<EditText>(R.id.et_password)
        val email = findViewById<EditText>(R.id.et_email)
        val registerButton = findViewById<Button>(R.id.btn_register)
        val showAllButton = findViewById<Button>(R.id.btn_show_all)

        val userDao = UserDatabase.getDatabase(this).userDao()
        userRepository = UserRepository(userDao)

        registerButton.setOnClickListener {
            val user = User(
                registrationNo = registrationNo.text.toString(),
                name = name.text.toString(),
                mobile = mobile.text.toString(),
                password = password.text.toString(),
                email = email.text.toString()
            )
            lifecycleScope.launch {
                userRepository.insertUser(user)
                Toast.makeText(this@MainActivity, "Registered Successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        showAllButton.setOnClickListener {
            lifecycleScope.launch {
                val users = userRepository.getAllUsers()
                users.forEach {
                    Toast.makeText(this@MainActivity, "User: ${it.name}, Email: ${it.email}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
