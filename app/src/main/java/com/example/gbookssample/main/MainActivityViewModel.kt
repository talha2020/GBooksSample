package com.example.gbookssample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gbookssample.GBooksRepository
import com.example.gbookssample.api.ApiError
import com.example.gbookssample.com.example.gbookssample.data.UIResponse
import com.example.gbookssample.com.example.gbookssample.data.Volume
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
            response.error?.also {
                booksMutableLiveData.value = UIResponse.Error(ApiError(code = it.code, message = it.message))
            }?: response.data?.let {
                booksMutableLiveData.value = UIResponse.Data(it)
            }

        }

    }

}