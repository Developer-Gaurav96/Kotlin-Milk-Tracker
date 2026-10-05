package com.example.milktracker.ui.screen.calendar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.milktracker.ui.components.CalendarDay
import com.example.milktracker.ui.components.CalendarHeatmap

@Stable
data class CalendarUiData(
    val days: List<CalendarDay> = emptyList(),
    val monthLabel: String = "October 2026",
    val selectedDate: String = ""
)

@Composable
fun CalendarScreen(
    uiData: CalendarUiData = CalendarUiData(),
    onDaySelect: (CalendarDay) -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { 12 })
    val showSheet = remember { mutableStateOf(false) }
    val selectedDay = remember { mutableStateOf(CalendarDay("")) }

    val monthDays by remember { derivedStateOf { uiData.days.filter { it.dateKey.startsWith("2026-10") } } }

    // Spring animation for bottom sheet appearance
    val sheetAnimation = spring<Float>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    )
    val onDayTapped = remember(onDaySelect) { { d: CalendarDay -> onDaySelect(d) } }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Calendar") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet.value = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text(
                text = uiData.monthLabel,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                val pageDays = remember(page) { uiData.days.filter { it.dateKey.contains("2026-${(page + 10).toString().padStart(2, '0')}" } }
                CalendarHeatmap(
                    days = pageDays,
                    modifier = Modifier.fillMaxSize().padding(8.dp),
                    onDayClick = { day ->
                        selectedDay.value = day
                        showSheet.value = true
                        onDayTapped(day)
                    }
                )
            }

            // Spring animated bottom sheet on tap
                // Bottom sheet uses ModalBottomSheet with spring animation
                if (showSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = { showSheet.value = false },
                        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text("Selected: ${selectedDay.value.dateKey}", style = MaterialTheme.typography.titleLarge)
                            Text("Amount: %.1f L".format(selectedDay.value.amount))
                        }
                    }
                }
            }
        }
    }
}
