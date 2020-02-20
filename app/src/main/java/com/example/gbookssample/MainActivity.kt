package com.example.gbookssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        mainActivityViewModel.getBooks("Titan")

    }
}
