package com.example.api.data.network

import com.example.api.data.model.Task
import com.example.api.data.model.TaskList
import com.example.api.data.model.TaskResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    // 1. ✅ FIX LỖI: PHẢI trả về List<Task> để khớp với dữ liệu API thực tế
    @GET("researchUTH/tasks")
    suspend fun getAllTasks(): Response<TaskList>

    // 2. ✅ ĐÚNG:
    @GET("researchUTH/task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): Response<TaskResponse>

    @DELETE("researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<Unit>
}