package com.example.bt_tuan3.ui.giaodien

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ComponentDetailScreen(navController: NavController, type: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        TextButton(onClick = { navController.popBackStack() }) {
            Text("←", fontSize = 32.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "$type Detail",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007BFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))


        when (type) {
            "Text" -> {
                val annotatedText = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 20.sp)) {
                        append("The ")
                    }
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFFC0505),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Brown\n")
                    }
                    append("fox j u m p s ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over\n")
                    }
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the ")
                    }
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("lazy ")
                    }
                    append("dog.")
                }
                Text(text = annotatedText, lineHeight = 30.sp)
            }

            "Image" -> { Text("Chi tiết cho Image", modifier = Modifier.align(Alignment.CenterHorizontally)) }
            "TextField" -> { Text("Chi tiết cho TextField", modifier = Modifier.align(Alignment.CenterHorizontally)) }
            else -> { Text("Chi tiết cho '$type'", modifier = Modifier.align(Alignment.CenterHorizontally)) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComponentDetailPreview() {
    val navController = rememberNavController()
    ComponentDetailScreen(navController = navController, type = "Text")
}
