package com.example.abdullahabdeen.hw2.bookdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abdullahabdeen.abdullah_abdeen_hw2.model.Book

@Database(entities = [Book::class], version = 4)
abstract class BookRoomDB : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDB? = null

        fun getDatabase(context: Context): BookRoomDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDB::class.java,
                    "book_database" // Ensure the database name is consistent
                ).fallbackToDestructiveMigration() // Handles version upgrades
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}