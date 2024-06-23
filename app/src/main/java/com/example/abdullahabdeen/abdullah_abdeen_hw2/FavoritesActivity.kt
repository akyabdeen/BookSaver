package com.example.abdullahabdeen.abdullah_abdeen_hw2

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.abdullahabdeen.abdullah_abdeen_hw2.databinding.ActivityFavoritesBinding
import com.example.abdullahabdeen.abdullah_abdeen_hw2.model.Book
import com.example.abdullahabdeen.hw2.adapter.BookRecyclerViewAdapter
import com.example.abdullahabdeen.hw2.bookdb.BookRoomDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesActivity : AppCompatActivity() {

    lateinit var layoutManager : LinearLayoutManager
    private lateinit var binding: ActivityFavoritesBinding
    private var bookAdapter: BookRecyclerViewAdapter ?= null

    private val bookDB: BookRoomDB = BookRoomDB.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        binding.rvFavorites.setLayoutManager(layoutManager)

        getData()
    }

    private fun displayBooks(books: List<Book>) {
        bookAdapter = BookRecyclerViewAdapter(this, books as ArrayList<Book>)
        binding.rvFavorites.setAdapter(bookAdapter)
        bookAdapter?.notifyDataSetChanged()
    }

    fun getData(){
        var bookList : MutableList<Book> = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {
            bookList = bookDB.bookDao().getAllBooks() as MutableList<Book>
            Log.d("YOOOO we here", bookList.toString())

            displayBooks(bookList as List<Book>)
        }
    }
}