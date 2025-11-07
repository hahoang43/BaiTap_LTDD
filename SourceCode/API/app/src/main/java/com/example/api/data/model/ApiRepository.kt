package com.example.api.data.model

data class ApiResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>,)