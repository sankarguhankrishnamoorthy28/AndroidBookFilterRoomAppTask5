package com.example.bookfilterroomapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val book_id: Int,
    val author: String,
    val country: String,
    val imageLink: String,
    val language: String,
    val link: String,
    val pages: Int,
    val title: String,
    val year: Int
)

