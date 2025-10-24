package com.example.quanlythuvien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.quanlythuvien.ui.screens.Book
import com.example.quanlythuvien.ui.screens.BottomNavBar
import com.example.quanlythuvien.ui.screens.HocSinhScreen
import com.example.quanlythuvien.ui.screens.QuanLyScreen
import com.example.quanlythuvien.ui.screens.SachScreen
import com.example.quanlythuvien.ui.screens.Student
import com.example.quanlythuvien.ui.theme.QuanLyThuVienTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuanLyThuVienTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    var books by remember {
        mutableStateOf(listOf(Book("Sách 01"), Book("Sách 02")))
    }
    var students by remember {
        mutableStateOf(
            listOf(
                Student("Nguyễn Văn A", listOf(Book("Sách 01"))),
                Student("Trần Thị B")
            )
        )
    }

    fun addBook(title: String) {
        if (title.isNotBlank()) {
            books = books + Book(title)
        }
    }

    fun addStudent(name: String) {
        if (name.isNotBlank()) {
            students = students + Student(name)
        }
    }

    fun borrowBook(studentName: String, bookTitle: String) {
        students = students.map {
            if (it.name == studentName) {
                val updatedBooks = it.borrowedBooks + Book(bookTitle)
                it.copy(borrowedBooks = updatedBooks)
            } else {
                it
            }
        }
    }

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, currentRoute = currentRoute) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "library",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("library") {
                QuanLyScreen(
                    students = students,
                    books = books,
                    onBorrowBook = ::borrowBook
                )
            }
            composable("books") {
                SachScreen(books = books, onAddBook = ::addBook)
            }
            composable("students") {
                HocSinhScreen(students = students, onAddStudent = ::addStudent)
            }
        }
    }
}
