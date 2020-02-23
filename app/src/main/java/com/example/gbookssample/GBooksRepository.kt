package com.example.gbookssample

import com.example.gbookssample.api.ApiClient
import com.example.gbookssample.api.ApiResponse
import javax.inject.Inject

class GBooksRepository @Inject constructor(private val apiClient: ApiClient) {

    // In bigger projects, having interfaces for repositories and apiclient can be helpful - kept it simple here.
    suspend fun getBooks(query: String): ApiResponse<Volume> {
        return apiClient.getBooks(query)
        // Here we can do further tasks if needed like saving/caching data or accessing user credentials and stuff.
    }

}