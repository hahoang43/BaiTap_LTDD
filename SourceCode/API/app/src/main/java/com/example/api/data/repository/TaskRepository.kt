package com.example.api.data.repository

import com.example.api.data.model.Task
import com.example.api.data.model.TaskList
import com.example.api.data.model.TaskResponse
import com.example.api.data.network.ApiService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskRepository {
    private val api: ApiService = Retrofit.Builder()
        .baseUrl("https://amock.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    // ✅ FIX LỖI: Sửa kiểu trả về thành List<Task> để khớp với ApiService
    suspend fun getAllTasks(): Response<TaskList> = api.getAllTasks()

    // Đã sửa lỗi parsing Detail trước đó
    suspend fun getTaskDetail(id: Int): Response<TaskResponse> = api.getTaskDetail(id)

    suspend fun deleteTask(id: Int): Response<Unit> = api.deleteTask(id)
}