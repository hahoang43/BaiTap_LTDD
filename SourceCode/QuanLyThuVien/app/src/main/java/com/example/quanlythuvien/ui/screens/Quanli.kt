package com.example.quanlythuvien.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuanLyScreen(
    students: List<Student>,
    books: List<Book>,
    onBorrowBook: (String, String) -> Unit
) {
    var selectedStudent by remember { mutableStateOf<Student?>(null) }
    var selectedBook by remember { mutableStateOf<Book?>(null) }
    var studentMenuExpanded by remember { mutableStateOf(false) }
    var bookMenuExpanded by remember { mutableStateOf(false) }

    // Define a custom color scheme for the dropdowns
    val customDropdownColors = MaterialTheme.colorScheme.copy(
        surfaceContainer = Color(0xFFFFE0B2) // Light Orange
    )

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Hệ thống Quản lý Thư viện", style = MaterialTheme.typography.titleLarge, color = Color.Black)


            Box {
                OutlinedTextField(
                    value = selectedStudent?.name ?: "Chọn sinh viên",
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Sinh viên") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { studentMenuExpanded = true },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledBorderColor = Color.Gray,
                        disabledPlaceholderColor = Color.DarkGray,
                        disabledLabelColor = Color.DarkGray,
                    )
                )
                MaterialTheme(colorScheme = customDropdownColors) {
                    DropdownMenu(
                        expanded = studentMenuExpanded,
                        onDismissRequest = { studentMenuExpanded = false }
                    ) {
                        students.forEach { student ->
                            DropdownMenuItem(
                                text = { Text(student.name, color = Color.Black) },
                                onClick = {
                                    selectedStudent = student
                                    studentMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }


            Box {
                OutlinedTextField(
                    value = selectedBook?.title ?: "Chọn sách",
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Sách") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { bookMenuExpanded = true },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledBorderColor = Color.Gray,
                        disabledPlaceholderColor = Color.DarkGray,
                        disabledLabelColor = Color.DarkGray,
                    )
                )
                MaterialTheme(colorScheme = customDropdownColors) {
                    DropdownMenu(
                        expanded = bookMenuExpanded,
                        onDismissRequest = { bookMenuExpanded = false }
                    ) {
                        books.forEach { book ->
                            DropdownMenuItem(
                                text = { Text(book.title, color = Color.Black) },
                                onClick = {
                                    selectedBook = book
                                    bookMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            // Borrow button
            Button(
                onClick = {
                    if (selectedStudent != null && selectedBook != null) {
                        onBorrowBook(selectedStudent!!.name, selectedBook!!.title)
                    }
                },
                enabled = selectedStudent != null && selectedBook != null,
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Blue, // Always blue
                    disabledContentColor = Color.White    // Always white
                )
            ) {
                Text("Cho mượn")
            }

            Spacer(Modifier.height(16.dp))


            Text("Thông tin mượn sách", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            LazyColumn {
                items(students) { student ->
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text("👤 ${student.name}", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
                        if (student.borrowedBooks.isNotEmpty()) {
                            student.borrowedBooks.forEach {
                                Text("  - 📘 ${it.title}", color = Color.Black)
                            }
                        } else {
                            Text("  (Chưa mượn sách nào)", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}