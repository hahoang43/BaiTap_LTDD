package com.example.api.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource // Cần import này cho Logo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.data.model.Task
import com.example.api.data.repository.TaskRepository
import com.example.api.R

// Màu nền cho từng trạng thái Task (theo ảnh)
val ColorTaskInProgress = Color(0xFFFFE1E1) // Hồng nhạt
val ColorTaskPending = Color(0xFFDDFEE2)    // Xanh nhạt
val ColorTaskDefault = Color(0xFFDCEAFE)    // Xanh dương nhạt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(onTaskClick: (Int) -> Unit) {
    val repo = remember { TaskRepository() }
    var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Giả lập gọi API
    LaunchedEffect(Unit) {
        val response = repo.getAllTasks()
        if (response.isSuccessful) {
            tasks = response.body()?.tasks ?: emptyList()
        } else {
            Log.e("SmartTasks", "Error fetching tasks: ${response.code()}")
        }
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                // ✅ THAY THẾ BIỂU TƯỢNG BÊN TRÁI BẰNG LOGO UTH
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý sự kiện Log out nếu cần */ }) {
                        Icon(
                            // ⚠️ THAY THẾ R.drawable.logo_uth BẰNG ID RESOURCE THỰC TẾ CỦA BẠN
                            painter = painterResource(id = R.drawable.logo_uth),
                            contentDescription = "UTH Logo",
                            modifier = Modifier.size(28.dp).padding(start = 8.dp),
                            tint = Color.Unspecified // Giữ màu gốc của ảnh
                        )
                    }
                },
                title = {
                    Column(modifier = Modifier.padding(start = 0.dp)) {
                        Text(
                            text = "SmartTasks",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            color = Color(0xFF5A5A5A)
                        )
                        Text(
                            text = "A simple and efficient to-do app",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                },
                actions = {
                    // ✅ CHỈ GIỮ LẠI NÚT THÔNG BÁO (CHUÔNG)
                    IconButton(onClick = { /* Xử lý sự kiện thông báo */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color(0xFFFFCC00)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        // ✅ XÓA KHỐI floatingActionButton
        // floatingActionButton = { ... }

        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                tonalElevation = 4.dp
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly, // Dùng SpaceEvenly cho 5 icons
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Icons Trái
                    IconButton(onClick = { /* Home */ }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { /* Calendar */ }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Calendar")
                    }
                    // ✅ NÚT ADD (+) Ở TRUNG TÂM
                    IconButton(onClick = { /* Add task */ }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    // Icons Phải
                    IconButton(onClick = { /* Files/Tasks */ }) {
                        Icon(Icons.Default.Description, contentDescription = "Files")
                    }
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            }
        }
    ) { innerPadding ->
        when {
            isLoading -> Box(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

            tasks.isEmpty() -> EmptyView(innerPadding)

            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks) { task ->
                    TaskItem(task) {
                        if (task.id != null) onTaskClick(task.id)
                    }
                }
            }
        }
    }
}

// ---------------------------------------------------------------------------------------
// --- TASK ITEM + EMPTY VIEW UI (Giữ nguyên) ---
// ---------------------------------------------------------------------------------------

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    // Logic Icon: Dấu check khi Completed/In Progress, Ô vuông khi Pending
    val showCheckmark = task.status == "In Progress" || task.status == "Completed"

    val cardColor = when (task.status) {
        "In Progress" -> ColorTaskInProgress
        "Pending" -> ColorTaskPending
        "Completed" -> ColorTaskInProgress
        else -> ColorTaskDefault
    }

    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Icon trạng thái (Checkbox/Checkmark)
                Box(
                    modifier = Modifier.padding(end = 8.dp).size(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (showCheckmark) {
                        // Dấu Check (Fill) cho trạng thái đã hoàn thành/đang tiến hành
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Checked",
                            tint = Color(0xFF5CB85C),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        // Ô vuông rỗng cho trạng thái Pending
                        Icon(
                            imageVector = Icons.Default.CheckBoxOutlineBlank,
                            contentDescription = "Unchecked",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Nội dung Task
                Text(
                    text = task.title ?: "Untitled Task",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(4.dp))
            Text(
                text = task.description ?: "No description provided.",
                style = MaterialTheme.typography.bodySmall,
                color = Color.DarkGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Status: ${task.status ?: "N/A"}",
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                    color = Color.Black
                )
                Text(
                    text = task.createdAt ?: "00:00 00-00",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun EmptyView(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Icon List/Task và Icon Ngủ (Zzz)
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Assignment,
                    contentDescription = "Task List",
                    modifier = Modifier.size(90.dp),
                    tint = Color.Gray
                )
                Icon(
                    imageVector = Icons.Default.Bed, // Giả lập Zzz
                    contentDescription = "Sleeping",
                    modifier = Modifier.size(30.dp).align(Alignment.TopEnd).offset(x = (-10).dp, y = (-10).dp),
                    tint = Color.Black
                )
            }

            Spacer(Modifier.height(16.dp))
            Text(
                "No Tasks Yet!",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                "Stay productive—add something to do.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
        }
    }
}