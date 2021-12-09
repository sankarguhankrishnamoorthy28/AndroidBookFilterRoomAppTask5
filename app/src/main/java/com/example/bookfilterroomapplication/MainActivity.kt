package com.example.bookfilterroomapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookfilterroomapplication.data.Author
import com.example.bookfilterroomapplication.data.Book
import com.example.bookfilterroomapplication.data.BookViewModel
import com.example.bookfilterroomapplication.data.BooksApplication
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var mBookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        findViewById<TextView>(R.id.bookApi).setOnClickListener()
        {
            insertDataToDatabase()
        }

        val adapter = BooksAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)

        val author=findViewById<TextInputLayout>(R.id.author_txt)?.editText?.text

        findViewById<Button>(R.id.button).setOnClickListener()
        {
            findViewById<TextView>(R.id.id_book).text="Id"
            findViewById<TextView>(R.id.title_book).text="Book Title"
            findViewById<TextView>(R.id.year_book).text="Year"
            mBookViewModel.getBooksByAuthor(author.toString()).observe(this,{book->
                adapter.setData(book)
            })
        }
    }

    private fun insertDataToDatabase() {
        val bookApplication=application as BooksApplication
        val bookService=bookApplication.books

        CoroutineScope(Dispatchers.IO).launch {
            val decodedBook = bookService.getBooks()
            withContext(Dispatchers.Main)
            {
                for(myData in decodedBook) {
                    val book =
                        Book(0,myData.author, myData.country, myData.imageLink, myData.language, myData.link, myData.pages.toInt(), myData.title, myData.year.toInt())
                    val author= Author(0,myData.author,myData.country)
                    mBookViewModel.addAuthor(author)
                    mBookViewModel.addBook(book)
                }
            }
        }
    }
}