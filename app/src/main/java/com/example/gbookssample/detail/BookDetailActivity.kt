package com.example.gbookssample.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gbookssample.BOOK_KEY
import com.example.gbookssample.Item
import com.example.gbookssample.R
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

        // Not showing a lot more info here on detail page as it's a design matter not related to the assignment.
    }
}
