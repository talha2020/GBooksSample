package com.example.gbookssample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gbookssample.GBooksRepository
import com.example.gbookssample.UIResponse
import com.example.gbookssample.Volume
import com.example.gbookssample.api.ApiError
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor
    (private val gBooksRepository: GBooksRepository): ViewModel() {

    private val booksMutableLiveData = MutableLiveData<UIResponse<Volume>>()
    val booksLiveData: LiveData<UIResponse<Volume>> = booksMutableLiveData

    fun getBooks(query: String){
        booksMutableLiveData.value = UIResponse.Loading
        viewModelScope.launch {
            val response = gBooksRepository.getBooks(query)
            //Here we can transform the data to the form that the view wants. For the purpose
            //of this assignment, just passing the same data wrapped in UIResponse sealed class.
            response.error?.also {
                booksMutableLiveData.value = UIResponse.Error(ApiError(code = it.code, message = it.message))
            }?: response.data?.let {
                booksMutableLiveData.value = UIResponse.Data(it)
            }

        }

    }

}