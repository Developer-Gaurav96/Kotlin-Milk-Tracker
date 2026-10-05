package com.example.milktracker.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Stable
open class CalendarDay(
    val dateKey: String,
    val logged: Boolean = false,
    val missed: Boolean = false,
    val multiShift: Boolean = false,
    val amount: Float = 0f
)

@Composable
fun CalendarHeatmap(
    days: List<CalendarDay>,
    modifier: Modifier = Modifier.fillMaxSize(),
    onDayClick: (CalendarDay) -> Unit = {}
) {
    val filtered by remember { derivedStateOf { days } }
    val cols = 7
    val cellColors = remember {
        mapOf(
            "logged" to Color(0xFF66BB6A),
            "missed" to Color(0xFFEF5350),
            "multi" to Color(0xFF42A5F5)
        )
    }

    Canvas(
        modifier = modifier
            .graphicsLayer(alpha = 0.98f)
            .pointerInput(Unit) { detectTapGestures { } }
    ) {
        val currentDays = filtered
        val cellW = size.width / cols
        val rows = (currentDays.size + cols - 1) / cols
        val cellH = if (rows > 0) size.height / rows else size.height

        currentDays.forEachIndexed { index, day ->
            val row = index / cols
            val col = index % cols
            val x = col * cellW
            val y = row * cellH

            val color = when {
                day.multiShift -> cellColors["multi"]!!
                day.missed -> cellColors["missed"]!!
                day.logged -> cellColors["logged"]!!
                else -> Color.LightGray.copy(alpha = 0.4f)
            }

            drawRect(
                color = color,
                topLeft = Offset(x + 2.dp.toPx(), y + 2.dp.toPx()),
                size = androidx.compose.ui.geometry.Size(cellW - 4.dp.toPx(), cellH - 4.dp.toPx())
            )
        }
    }
}
