package com.example.bookfilterroomapplication.data


import retrofit2.http.GET

interface BookInterface {
    @GET("/books")
    suspend fun getBooks():List<AllBooks>
}