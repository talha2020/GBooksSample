package com.example.gbookssample.api

import android.util.Log
import com.example.gbookssample.BuildConfig
import com.example.gbookssample.com.example.gbookssample.api.ApiEndPoints
import com.example.gbookssample.com.example.gbookssample.data.Volume
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class ApiClient @Inject constructor() {
    private val client = HttpClient(OkHttp) {
        engine {
            if (BuildConfig.DEBUG){
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY

                addInterceptor(logging)
                addNetworkInterceptor(StethoInterceptor())
            }
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        expectSuccess = true
    }

    suspend fun getBooks(query: String){
        val url = ApiEndPoints.FETCH_BOOKS
        val res = client.get<Volume>(url) {
            parameter("q", query)
        }
    }

}