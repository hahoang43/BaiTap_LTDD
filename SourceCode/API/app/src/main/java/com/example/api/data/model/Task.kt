package com.example.api.data.model

import com.google.gson.annotations.SerializedName
data class Task(
    val id: Int,
    // ✅ FIX LỖI: Khai báo title là nullable (String?)
    val title: String?,
    val description: String?,
    // ✅ FIX LỖI: Khai báo status là nullable (String?) - Dù API có vẻ luôn có, nên an toàn
    val status: String?,
    val priority: String?,
    val category: String?,
    val dueDate: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val subtasks: List<SubTask>?,
    val attachments: List<Attachment>?
)

// Các model TaskList và TaskResponse giữ nguyên như bạn đã sửa
data class TaskList(
    @SerializedName("data")
    val tasks: List<Task>
)

data class TaskResponse(
    @SerializedName("data")
    val task: Task
)
// (Và các model SubTask, Attachment nếu có)