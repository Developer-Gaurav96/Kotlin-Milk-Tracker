package com.example.milktracker.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.milktracker.data.local.entity.Entry

@Dao
interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: Entry): Long

    @Query("SELECT * FROM entries WHERE dateEpoch BETWEEN :start AND :end ORDER BY dateEpoch DESC")
    fun entriesInRange(start: Long, end: Long): Flow<List<Entry>>

    @Query("SELECT * FROM entries WHERE dateEpoch = :dayEpoch ORDER BY shift")
    fun entriesForDay(dayEpoch: Long): Flow<List<Entry>>

    @Query("SELECT shift FROM entries WHERE dateEpoch = :dayEpoch")
    suspend fun shiftsForDay(dayEpoch: Long): List<String>

    @Query("SELECT SUM(quantityLiters) FROM entries WHERE dateEpoch BETWEEN :start AND :end")
    fun totalQuantityInRange(start: Long, end: Long): Flow<Float?>

    @Query("SELECT SUM(billAmount) FROM entries WHERE dateEpoch BETWEEN :start AND :end")
    fun totalBillInRange(start: Long, end: Long): Flow<Float?>

    @Delete
    suspend fun delete(entry: Entry)

    @Query("SELECT COUNT(DISTINCT dateEpoch) FROM entries WHERE dateEpoch BETWEEN :start AND :end")
    fun daysLoggedInRange(start: Long, end: Long): Flow<Int?>
}
