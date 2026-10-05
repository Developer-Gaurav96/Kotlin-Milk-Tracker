package com.example.milktracker.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.max

@Composable
fun BillChart(
    data: List<Pair<String, Float>>,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val keyData = remember(data) { data.map { it.first to it.second } }
    val anim = remember { Animatable(0f) }
    val maxVal = remember(data) { max(1f, data.maxOfOrNull { it.second } ?: 1f) }

    LaunchedEffect(keyData) {
        anim.snapTo(0f)
        anim.animateTo(1f, animationSpec = tween(900, delayMillis = 150))
    }

    val colors = remember {
        listOf(Color(0xFF81C784), Color(0xFF4DB6AC), Color(0xFFFFE082), Color(0xFFFFB74D), Color(0xFFF06292))
    }

    Canvas(modifier = modifier.pointerInput(Unit) { detectTapGestures { } }) {
        val count = max(data.size, 1)
        val slot = size.width / count
        val barW = slot * 0.65f
        val gap = slot * 0.35f
        val chartH = size.height * 0.85f
        data.forEachIndexed { i, (_, value) ->
            val x = i * slot + gap / 2
            val h = (value / maxVal) * chartH * anim.value
            val c = colors[i % colors.size]
            drawRect(color = c.copy(alpha = 0.92f), topLeft = Offset(x, size.height - h), size = androidx.compose.ui.geometry.Size(barW, h))
        }
    }
}
