package com.example.floatingactionbutton

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.floatingactionbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var addFAB: FloatingActionButton
    private lateinit var homeFAB: FloatingActionButton
    private lateinit var settingsFAB: FloatingActionButton
    private var fabVisible = false
    private lateinit var activityMainBinding: ActivityMainBinding

//    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        addFAB =activityMainBinding.idFABAdd
        homeFAB = activityMainBinding.idFABHome
        settingsFAB = activityMainBinding.idFABSettings

        ViewCompat.setOnApplyWindowInsetsListener(activityMainBinding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addFAB.setOnClickListener {
            if (!fabVisible) {
                homeFAB.show()
                settingsFAB.show()
                addFAB.setImageResource(R.drawable.baseline_close_24)
                fabVisible = true
            } else {
                homeFAB.hide()
                settingsFAB.hide()
                addFAB.setImageResource(R.drawable.baseline_add_24)
                fabVisible = false
            }
        }

        homeFAB.setOnClickListener {
            // Handle home FAB click
        }

        settingsFAB.setOnClickListener {
            // Handle settings FAB click
        }
    }
}