package com.example.bt_th2

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val btnTao = findViewById<Button>(R.id.btnTao)
        val listContainer = findViewById<LinearLayout>(R.id.listContainer)

        btnTao.setOnClickListener {
            val inputText = edtNumber.text.toString().trim()
            listContainer.removeAllViews()

            val number = inputText.toIntOrNull()
            if (number != null && number > 0) {
                for (i in 1..number) {
                    val btn = Button(this)
                    btn.text = i.toString()
                    btn.setBackgroundColor(Color.RED)
                    btn.setTextColor(Color.WHITE)
                    btn.setPadding(10, 20, 10, 20)
                    listContainer.addView(btn)
                }
            } else {
                Toast.makeText(this, "Dữ liệu bạn nhập không hợp lệ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
