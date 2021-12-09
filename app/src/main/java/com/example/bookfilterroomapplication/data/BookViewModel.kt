package com.example.bookfilterroomapplication.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    private val repository: BookRepository
    val readAllBooks:LiveData<List<Book>>
    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository= BookRepository(bookDao)
        readAllBooks=bookDao.readAllBooks()
    }

    fun getBooksByAuthor(author:String):LiveData<List<Book>>
    {
        return repository.getBooksByAuthor(author)
    }

    fun addBook(book: Book) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }
    fun addAuthor(author: Author)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAuthor(author)
        }
    }
}