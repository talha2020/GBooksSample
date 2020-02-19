package com.example.gbookssample.com.example.gbookssample.api

object ApiEndPoints {
    const val FETCH_BOOKS = "https://www.googleapis.com/books/v1/volumes?"
    //I would usually create config files for each flavor like release/InternalConfig and debug/InternalConfig
    // and put the endpoint for different flavours there and reference them from here like
    // const val FETCH_BOOKS = InternalConfig.GOOGLE_BOOKS_QUERY_ENDPOINT but for this sample it's not really needed

}