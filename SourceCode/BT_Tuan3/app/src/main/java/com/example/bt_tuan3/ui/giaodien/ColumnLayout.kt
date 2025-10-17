package com.example.bt_tuan3.ui.giaodien

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ColumnLayoutScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        TextButton(onClick = { navController.popBackStack() }) {
            Text("←", fontSize = 32.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.Blue)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ColumnLayoutScreenPreview() {
    val navController = rememberNavController()
    ColumnLayoutScreen(navController = navController)
}
