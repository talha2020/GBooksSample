package com.example.gbookssample.com.example.gbookssample

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gbookssample.GenericAdapter
import com.example.gbookssample.R
import com.example.gbookssample.com.example.gbookssample.data.Item

class BooksListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    GenericAdapter.Binder<Item>  {

    private var titleTv = itemView.findViewById<TextView>(R.id.titleTv)
    //private var subtitleTv = itemView.findViewById<TextView>(R.id.subtitleTv)
    private var authorTv = itemView.findViewById<TextView>(R.id.authorTv)

    override fun bind(data: Item) {
        titleTv.text = data.volumeInfo.title
        //subtitleTv.text = data.volumeInfo.subtitle
        authorTv.text = String.format("Author: %s", data.volumeInfo.authors?.joinToString(separator = ", ")?: "")
    }

}