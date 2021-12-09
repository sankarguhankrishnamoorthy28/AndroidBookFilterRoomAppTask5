package com.example.bookfilterroomapplication.data

import androidx.lifecycle.LiveData
import com.example.bookfilterroomapplication.data.Author
import com.example.bookfilterroomapplication.data.Book
import com.example.bookfilterroomapplication.data.BookDao

class BookRepository(private val bookDao: BookDao) {

    val readAllBooks: LiveData<List<Book>> = bookDao.readAllBooks()

    fun getBooksByAuthor(author: String): LiveData<List<Book>> {
        return bookDao.readBooks(author)
    }

    fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    fun addAuthor(author: Author) {
        bookDao.addAuthor(author)
    }

}