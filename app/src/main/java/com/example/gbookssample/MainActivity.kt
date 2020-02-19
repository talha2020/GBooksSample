package com.example.gbookssample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        mainActivityViewModel.getBooks()
    }
}
