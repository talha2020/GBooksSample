package com.example.gbookssample

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gbookssample.com.example.gbookssample.data.UIResponse
import com.example.gbookssample.com.example.gbookssample.data.UIResponse.*
import com.example.gbookssample.com.example.gbookssample.data.Volume
import com.example.gbookssample.com.example.gbookssample.setGone
import com.example.gbookssample.com.example.gbookssample.show
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
                    Toast.makeText(this, "Total items: ${response.data.totalItems}", Toast.LENGTH_LONG).show()
                }
                is Error -> {
                    progressBar.setGone()
                    Toast.makeText(this, "error: ${response.error.message}", Toast.LENGTH_LONG).show()
                }
            }

        })

        //mainActivityViewModel.getBooks("Titan")

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

}
