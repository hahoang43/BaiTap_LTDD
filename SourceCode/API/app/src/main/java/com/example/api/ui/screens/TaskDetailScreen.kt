package com.example.api.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.api.data.model.Task
import com.example.api.data.model.SubTask
import com.example.api.data.model.Attachment
import com.example.api.data.repository.TaskRepository
import kotlinx.coroutines.launch
// Thêm icons cần thiết cho Tags
import androidx.compose.material.icons.filled.GridView // Cho Category
import androidx.compose.material.icons.filled.ListAlt // Cho Status
import androidx.compose.material.icons.filled.Flag // Cho Priority
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(taskId: Int, onBack: () -> Unit) {
    val repo = remember { TaskRepository() }
    var task by remember { mutableStateOf<Task?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var fetchError by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    LaunchedEffect(taskId) {
        val response = repo.getTaskDetail(taskId)
        if (response.isSuccessful) {
            task = response.body()?.task
            fetchError = task == null
        } else {
            task = null
            fetchError = true
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                // ✅ Tiêu đề (Sử dụng Row để căn giữa)
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Spacer 1: Cân bằng Icon Quay lại
                        Spacer(Modifier.width(48.dp))

                        Text(
                            text = "Detail",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )

                        // Spacer 2: Cân bằng Icon Xóa
                        if (!isLoading && task != null) {
                            IconButton(onClick = {
                                scope.launch {
                                    repo.deleteTask(task!!.id)
                                    onBack()
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color(0xFFFFCC00))
                            }
                        } else {
                            Spacer(Modifier.width(48.dp))
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go back")
                    }
                },
                actions = {}
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (task == null || fetchError) {
            // Màn hình lỗi "Task not found"
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Task not found or unable to load.", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = onBack) {
                        Text("Go Back")
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp) // Thêm padding ngang
            ) {
                task?.let { currentTask ->

                    Spacer(Modifier.height(8.dp))

                    // --- Header and Description ---
                    Text(currentTask.title ?: "Untitled Task", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(currentTask.description ?: "Finish the UI, integrate API, and write documentation", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)

                    Spacer(Modifier.height(16.dp))

                    // --- Tags (Category, Status, Priority) ---
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        // Màu và Icon được xác định dựa trên trường (Work -> Hồng, In Progress -> Đỏ)
                        Tag(
                            icon = Icons.Default.GridView,
                            label = currentTask.category ?: "Work",
                            color = Color(0xFFFFE1E1) // Màu nền chung của Tag
                        )
                        Tag(
                            icon = Icons.Default.ListAlt,
                            label = currentTask.status ?: "In Progress",
                            color = Color(0xFFFFE1E1)
                        )
                        Tag(
                            icon = Icons.Default.Flag,
                            label = currentTask.priority ?: "High",
                            color = Color(0xFFFFE1E1)
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    // --- Subtasks ---
                    Text("Subtasks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    currentTask.subtasks?.let { SubtaskList(it) }

                    Divider(Modifier.padding(vertical = 12.dp))

                    // --- Attachments ---
                    Text("Attachments", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    currentTask.attachments?.let { AttachmentList(it) }

                    Spacer(Modifier.height(40.dp))

                    // --- Nút Delete Task --- (Giấu đi để ưu tiên giao diện)
                    // Nếu bạn cần nút delete ở dưới, hãy hiển thị nó ở đây
                }
            }
        }
    }
}

// --------------------------------------------------------------------------------------------------
// --- COMPSABLES PHỤ (THẺ & DANH SÁCH) ---
// --------------------------------------------------------------------------------------------------

@Composable
// ✅ SỬA: Hàm Tag nhận ImageVector
fun Tag(icon: ImageVector, label: String, color: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.Black // Màu icon
            )
            Spacer(Modifier.width(4.dp))
            // Label
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun SubtaskList(subtasks: List<SubTask>) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        if (subtasks.isEmpty()) {
            Text("No subtasks available.", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        } else {
            subtasks.forEach { subtask ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = subtask.isCompleted,
                        onCheckedChange = { /* Xử lý cập nhật trạng thái nếu cần */ },
                        enabled = false,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = subtask.title ?: "Untitled Subtask",
                        style = MaterialTheme.typography.bodyLarge,
                        textDecoration = if (subtask.isCompleted) androidx.compose.ui.text.style.TextDecoration.LineThrough else null,
                        color = if (subtask.isCompleted) Color.Gray else Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun AttachmentList(attachments: List<Attachment>) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        if (attachments.isEmpty()) {
            Text("No attachments.", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        } else {
            attachments.forEach { attachment ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { /* Mở file */ },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "Attachment",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = attachment.fileName ?: "Unknown File",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            }
        }
    }
}