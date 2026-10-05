package com.example.milktracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.compose.runtime.Stable

@Stable
@Entity(tableName = "vendors")
data class Vendor(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val defaultRatePerLiter: Float,
    val isActive: Boolean = true,
    val colorHex: Int = 0xFF6366F1,
    val createdAt: Long = System.currentTimeMillis()
)
