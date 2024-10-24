package com.example.hostelf

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imageUri: String,
    val name: String,
    val mobile: String,
    val dob: String,
    val password: String
)
