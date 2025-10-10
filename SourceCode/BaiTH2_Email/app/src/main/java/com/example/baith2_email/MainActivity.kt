package com.example.baith2_email

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val btnCheck = findViewById<Button>(R.id.btnCheck)

        btnCheck.setOnClickListener {
            val email = edtEmail.text.toString().trim()

            if (email.isEmpty()) {
                tvMessage.text = "Email không hợp lệ"
                tvMessage.setTextColor(getColor(android.R.color.holo_red_dark))
            } else if (!email.contains("@")) {
                tvMessage.text = "Email không đúng định dạng"
                tvMessage.setTextColor(getColor(android.R.color.holo_red_dark))
            } else {
                tvMessage.text = "Bạn đã nhập email hợp lệ"
                tvMessage.setTextColor(getColor(android.R.color.holo_green_dark))
            }
        }
    }
}