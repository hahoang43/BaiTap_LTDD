package com.example.bt_tuan3.ui.giaodien

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class ComponentInfo(val title: String, val route: String?)

val displayComponents = listOf(
    ComponentInfo("Text", "text"),
    ComponentInfo("Image", "image")
)

val inputComponents = listOf(
    ComponentInfo("TextField", "textfield")
)

val layoutComponents = listOf(
    ComponentInfo("Column", "columnlayout"),
    ComponentInfo("Row", "rowlayout")
)

@Composable
fun ComponentsListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "UI Components List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007BFF),
            modifier = Modifier.padding(vertical = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Text("Display", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
            }
            items(displayComponents) { component ->
                ComponentListItem(component = component) {
                    component.route?.let { navController.navigate(it) }
                }
            }

            item {
                Text("Input", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
            }
            items(inputComponents) { component ->
                ComponentListItem(component = component) {
                    component.route?.let { navController.navigate(it) }
                }
            }

            item {
                Text("Layout", fontWeight = FontWeight.Bold, fontSize = 20.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
            }
            items(layoutComponents) { component ->
                ComponentListItem(component = component) {
                    component.route?.let { navController.navigate(it) }
                }
            }
        }
    }
}

@Composable
fun ComponentListItem(component: ComponentInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(enabled = component.route != null) { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD6EAF8))
    ) {
        Text(
            text = component.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ComponentsListScreenPreview() {
    val navController = rememberNavController()
    ComponentsListScreen(navController = navController)
}
