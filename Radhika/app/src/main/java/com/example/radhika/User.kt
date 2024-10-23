package com.example.radhika

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val registrationNo: String,
    val name: String,
    val mobile: String,
    val password: String,
    val email: String
)
