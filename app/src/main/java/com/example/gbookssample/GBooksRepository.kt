package com.example.gbookssample

import android.util.Log
import com.example.gbookssample.api.ApiClient
import javax.inject.Inject

class GBooksRepository @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getBooks(query: String){
        Log.i("getBooks", "GBooksRepository")
        apiClient.getBooks(query)
    }

}