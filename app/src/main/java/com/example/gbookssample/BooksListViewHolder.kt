package com.example.gbookssample.com.example.gbookssample

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gbookssample.GenericAdapter
import com.example.gbookssample.R
import com.example.gbookssample.com.example.gbookssample.data.Item

class BooksListViewHolder(itemView: View,
                          val onItemClick: (Item) -> Unit): RecyclerView.ViewHolder(itemView),
    GenericAdapter.Binder<Item>  {

    private var listItem = itemView.findViewById<LinearLayout>(R.id.listItem)
    private var titleTv = itemView.findViewById<TextView>(R.id.titleTv)
    private var authorTv = itemView.findViewById<TextView>(R.id.authorTv)

    override fun bind(data: Item) {
        titleTv.text = data.volumeInfo.title
        authorTv.text = String.format(itemView.context.getString(R.string.authors), data.volumeInfo.authors?.joinToString(separator = ", ")?: "")
        listItem.setOnClickListener { onItemClick(data) }
    }

}