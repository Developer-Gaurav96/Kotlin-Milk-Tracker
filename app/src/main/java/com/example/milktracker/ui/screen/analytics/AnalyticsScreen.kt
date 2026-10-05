package com.example.milktracker.ui.screen.analytics

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.milktracker.ui.components.BillChart

@Stable
data class AnalyticsUi(
    val totalLiters: Float = 0f,
    val totalRupees: Float = 0f,
    val avgDailyLiters: Float = 0f,
    val activeDays: Int = 0,
    val chartData: List<Pair<String, Float>> = emptyList(),
    val itemized: List<ItemRow> = emptyList()
)

@Stable
data class ItemRow(
    val vendor: String,
    val shift: String,
    val liters: Float,
    val amount: Float
)

@Composable
fun AnalyticsScreen(
    ui: AnalyticsUi = AnalyticsUi(),
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val kpiAnimation = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        kpiAnimation.animateTo(1f, animationSpec = tween(700))
    }

    // Stable lambda reference for item click (remembered at screen level)
    val onItemClick = remember { { item: ItemRow -> /* navigate to vendor detail */ } }

    // Derived state for monthly totals (computed from UI model)
    val monthlyTotal by remember { derivedStateOf { ui.chartData.sumOf { it.second } } }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Analytics", style = MaterialTheme.typography.headlineMedium)

        // Four KPI tiles
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KPIBox("Total L", "%.1f".format(ui.totalLiters * kpiAnimation.value))
            KPIBox("Total ₹", "%.0f".format(ui.totalRupees * kpiAnimation.value))
            KPIBox("Avg Daily L", "%.1f".format(ui.avgDailyLiters * kpiAnimation.value))
            KPIBox("Active Days", "${(ui.activeDays * kpiAnimation.value).toInt()}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bill chart with Animatable bar growth
        Card(modifier = Modifier.fillMaxWidth().height(240.dp)) {
            Box(modifier = Modifier.padding(8.dp)) {
                BillChart(data = ui.chartData, modifier = Modifier.fillMaxSize())
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Itemized breakdown with LazyColumn (key + contentType)
        Text("Breakdown", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(
                items = ui.itemized,
                key = { it.vendor + it.shift },
                contentType = { "item" }
            ) { item ->
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(item.vendor, style = MaterialTheme.typography.bodyLarge)
                        Text(item.shift, style = MaterialTheme.typography.bodySmall)
                    }
                    Text("%.1f L · ₹%.0f".format(item.liters, item.amount), style = MaterialTheme.typography.bodyMedium)
                }
                Divider()
            }
        }
    }
}

@Composable
private fun KPIBox(label: String, value: String) {
    Card(modifier = Modifier.weight(1f).padding(4.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(label, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(value, style = MaterialTheme.typography.titleLarge)
        }
    }
}
