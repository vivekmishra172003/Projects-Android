package com.example.hostel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String,
    val name: String,
    val password: String,
    val mobile: String
)
