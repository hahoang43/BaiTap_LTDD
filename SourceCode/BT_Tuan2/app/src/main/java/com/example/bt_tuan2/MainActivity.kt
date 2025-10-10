package com.example.bt_tuan2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnCheck.setOnClickListener {
            val name = edtName.text.toString().trim()
            val ageText = edtAge.text.toString().trim()

            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageText.toIntOrNull()
            if (age == null || age < 0) {
                Toast.makeText(this, "Tuổi không hợp lệ!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val group = when {
                age > 65 -> "Người già"
                age in 6..65 -> "Người lớn"
                age in 2..5 -> "Trẻ em"
                else -> "Em bé"
            }

            txtResult.text = "Họ tên: $name\nTuổi: $age\nPhân loại: $group"
        }
    }
}
