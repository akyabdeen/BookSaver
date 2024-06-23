package com.example.abdullahabdeen.hw2.bookdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.abdullahabdeen.abdullah_abdeen_hw2.model.Book

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book)

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<Book>

    @Delete
    fun delete(book: Book)
}