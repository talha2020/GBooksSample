package com.example.gbookssample.api

data class ApiError(
    val code: String? = "",
    val message: String? = "Unknown error occurred"
)