package com.example.abdullahabdeen.hw2.adapter

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abdullahabdeen.abdullah_abdeen_hw2.R
import com.example.abdullahabdeen.abdullah_abdeen_hw2.model.Book
import com.example.abdullahabdeen.hw2.bookdb.BookRoomDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookRecyclerViewAdapter(private val context: Context, private val recyclerItemValues: ArrayList<Book>)
    :
    RecyclerView.Adapter<BookRecyclerViewAdapter.CustomRecyclerViewItemHolder>() {

    var bookDB : BookRoomDB = BookRoomDB.getDatabase(context.applicationContext)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CustomRecyclerViewItemHolder {

        val inflator = LayoutInflater.from(context)
        val itemView = inflator.inflate(R.layout.book_item, viewGroup, false)
        return CustomRecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: CustomRecyclerViewItemHolder, position: Int) {
        val curentItem = recyclerItemValues[position]
        myRecyclerViewItemHolder.tvBookTitle.text = curentItem.title
        myRecyclerViewItemHolder.tvBookAuthor.text = curentItem.author

        Glide.with(myRecyclerViewItemHolder.itemView.context)
            .load(curentItem.coverImg)
            .into(myRecyclerViewItemHolder.imgBookCover)

        myRecyclerViewItemHolder.heartButton.setOnClickListener {
            addBookToFavorites(curentItem)
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    inner class CustomRecyclerViewItemHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvBookTitle: TextView
        var tvBookAuthor: TextView
        var imgBookCover: ImageView
        var heartButton: Button

        init {
            val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        removeBookFromFavorites(recyclerItemValues[position])
                    }
                    return true
                }
            })

            itemView.setOnTouchListener { v, event ->
                gestureDetector.onTouchEvent(event)
                true
            }

            tvBookTitle = itemView.findViewById(R.id.tvBookTitle)
            tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor)
            imgBookCover = itemView.findViewById(R.id.imgBookCover)
            heartButton = itemView.findViewById(R.id.heartButton)
        }
    }

    private fun addBookToFavorites(book : Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookDB.bookDao().insert(book)

            withContext(Dispatchers.Main) {
                showDialog(book)
            }
        }
    }

    private fun removeBookFromFavorites(book : Book){
        CoroutineScope(Dispatchers.IO).launch {
            bookDB.bookDao().delete(book)
            withContext(Dispatchers.Main){
                recyclerItemValues.remove(book)
                notifyDataSetChanged()
            }
        }
    }

    private fun showDialog(book: Book) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_book_added, null)
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        dialogTitle.text = "${book.title} added to Favorites!"

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        dialogView.findViewById<Button>(R.id.buttonOk).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}
