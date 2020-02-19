package com.example.gbookssample

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor
    (private val gBooksRepository: GBooksRepository): ViewModel() {

    fun getBooks(){
        Log.i("getBooks", "MainActivityViewModel")
        viewModelScope.launch {
            gBooksRepository.getBooks("Titan")
        }

    }

}