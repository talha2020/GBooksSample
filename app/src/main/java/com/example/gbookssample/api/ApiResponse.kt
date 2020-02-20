package com.example.gbookssample.api

data class ApiResponse<T>(
    val data: T? = null,
    val error: ApiError? = null
)