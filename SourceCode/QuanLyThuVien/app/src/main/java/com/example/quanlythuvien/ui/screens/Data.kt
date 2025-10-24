package com.example.quanlythuvien.ui.screens

data class Book(val title: String)
data class Student(val name: String, val borrowedBooks: List<Book> = emptyList())
