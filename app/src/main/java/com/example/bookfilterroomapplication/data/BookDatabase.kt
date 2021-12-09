package com.example.bookfilterroomapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookfilterroomapplication.data.Author
import com.example.bookfilterroomapplication.data.Book
import com.example.bookfilterroomapplication.data.BookDao

@Database(entities = [Book::class, Author::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object
    {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context):BookDatabase{

            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                context.deleteDatabase("books")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "books"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}