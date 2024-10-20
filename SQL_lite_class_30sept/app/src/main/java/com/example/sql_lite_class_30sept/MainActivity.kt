package com.example.sql_lite_class_30sept

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.text
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sql_lite_class_30sept.databinding.ActivityMainBinding
import kotlin.text.clear

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dbHelper: SQLLiteDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = SQLLiteDBHelper(this, null)

        binding.addName.setOnClickListener {
            val name = binding.enterName.text.toString()
            val age = binding.enterAge.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty()) {
                dbHelper.addName(name, age)
                binding.enterName.text.clear()
                binding.enterAge.text.clear()
                Toast.makeText(this, "Record added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter name and age", Toast.LENGTH_SHORT).show()
            }
        }

        // Add click listeners for other buttons (printName, delete) and implement their logic
    }
}