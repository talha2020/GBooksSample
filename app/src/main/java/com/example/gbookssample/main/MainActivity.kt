package com.example.gbookssample.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gbookssample.*
import com.example.gbookssample.UIResponse.*
import com.example.gbookssample.com.example.gbookssample.BooksListViewHolder
import com.example.gbookssample.detail.BookDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        mainActivityViewModel.booksLiveData.observe(this, Observer{ response ->
            when (response) {
                is Loading -> {
                    progressBar.show()
                }
                is Data<Volume> ->{
                    progressBar.setGone()
                    showBooks(response.data)
                }
                is Error -> {
                    progressBar.setGone()
                    showError("Error: ${response.error.message}")
                }
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)

        val searchMenuItem = menu?.findItem(R.id.search)
        val searchView = searchMenuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                query?.let { mainActivityViewModel.getBooks(it) }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        return true
    }

    private fun showBooks(volume: Volume){
        if (volume.items.isNullOrEmpty()){
            showError(getString(R.string.no_books_found))
            return
        }
        //TODO: Avoid creating a new adapter everytime
        // normally i would update the recycler view items and then notify the adapter. Leaving it like this due to shortage of time.

        val books = volume.items
        val adapter = object : GenericAdapter<Item>(books) {
            override fun getLayoutId(position: Int, obj: Item): Int {
                return R.layout.books_list_item
            }
            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return BooksListViewHolder(view, onItemClick = {
                    launchBookDetailActivity(it)
                })
            }
        }
        booksRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        booksRv.adapter = adapter
    }

    private fun launchBookDetailActivity(item: Item) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(BOOK_KEY, item)
        startActivity(intent)
    }

}
