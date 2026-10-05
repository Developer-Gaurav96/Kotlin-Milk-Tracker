package com.example.milktracker.ui.screen.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.milktracker.ui.theme.*

@Composable
fun DashboardScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Milk Tracker") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = DeepBlue,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }
            items(listOf(1, 2, 3)) { i ->
                Card(
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Milk)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Entry $i", style = MaterialTheme.typography.titleMedium, color = DarkBrown)
                        Text("1.5 L · ₹45", style = MaterialTheme.typography.bodyMedium, color = DeepBlue)
                    }
                }
            }
        }
    }
}
