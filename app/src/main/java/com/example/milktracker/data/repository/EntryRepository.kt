package com.example.milktracker.data.repository

import kotlinx.coroutines.flow.Flow
import com.example.milktracker.data.local.entity.Entry
import com.example.milktracker.data.local.entity.Vendor

interface EntryRepository {
    suspend fun addEntry(entry: Entry): Long
    fun entriesInMonth(startEpoch: Long, endEpoch: Long): Flow<List<Entry>>
    fun entriesForDay(dayEpoch: Long): Flow<List<Entry>>
    suspend fun getShiftsForDay(dayEpoch: Long): List<String>
    suspend fun deleteEntry(entry: Entry)
    fun totalQuantity(start: Long, end: Long): Flow<Float?>
    fun totalBill(start: Long, end: Long): Flow<Float?>
    fun daysLogged(start: Long, end: Long): Flow<Int?>
}
