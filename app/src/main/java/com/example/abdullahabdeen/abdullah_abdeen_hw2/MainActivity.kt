package com.example.abdullahabdeen.abdullah_abdeen_hw2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abdullahabdeen.abdullah_abdeen_hw2.databinding.ActivityMainBinding
import com.example.abdullahabdeen.abdullah_abdeen_hw2.databinding.ToolbarWithSearchAndFavoritesBinding
import com.example.abdullahabdeen.hw2.adapter.BookRecyclerViewAdapter
import com.example.abdullahabdeen.hw2.model.BookSys

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: BookRecyclerViewAdapter
    lateinit var layoutManager : LinearLayoutManager
    lateinit var favButton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favButton = findViewById(R.id.favButton)

        BookSys.prepareData()

        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.setLayoutManager(layoutManager)

        adapter = BookRecyclerViewAdapter(this, BookSys.bookList)
        binding.recyclerView.setAdapter(adapter)

        favButton.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}