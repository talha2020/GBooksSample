package com.example.gbookssample.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gbookssample.BOOK_KEY
import com.example.gbookssample.R
import com.example.gbookssample.com.example.gbookssample.data.Item
import com.example.gbookssample.showError
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val book = intent.getParcelableExtra<Item>(BOOK_KEY)
        showBookDetails(book)
    }

    private fun showBookDetails(book: Item) {
        Picasso.get().load(book.volumeInfo.imageLinks?.smallThumbnail).into(bookIv)
        titleTv.text = book.volumeInfo.title
        subtitleTv.text = book.volumeInfo.subtitle
        authorTv.text = String.format(getString(R.string.authors), book.volumeInfo.authors?.joinToString(separator = ", ")?: "")
    }
}
