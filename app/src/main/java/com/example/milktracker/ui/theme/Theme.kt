package com.example.milktracker.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DeepBlue,
    onPrimary = Color.White,
    secondary = Amber,
    onSecondary = Color.Black,
    background = Cream,
    onBackground = DarkBrown,
    surface = Milk,
    onSurface = DarkBrown,
    surfaceVariant = Color(0xFFEDE8DB),
    onSurfaceVariant = DarkBrown,
)

private val DarkColorScheme = darkColorScheme(
    primary = DeepBlue,
    onPrimary = Color.White,
    secondary = Amber,
    onSecondary = Color.Black,
    background = Color(0xFF2A2018),
    onBackground = Cream,
    surface = Color(0xFF3E2C1F),
    onSurface = Cream,
    surfaceVariant = Color(0xFF4A3628),
    onSurfaceVariant = Cream,
)

@Composable
fun MilkTrackerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
