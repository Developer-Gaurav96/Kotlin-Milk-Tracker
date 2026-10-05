package com.example.milktracker.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index
import androidx.compose.runtime.Stable

@Stable
@Entity(
    tableName = "entries",
    indices = [
        Index(value = ["dateEpoch", "shift"], unique = true)
    ]
)
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateEpoch: Long,
    val quantityLiters: Float,
    val ratePerLiter: Float,
    val vendorId: Long,
    val shift: String,
    val notes: String = "",
    val billAmount: Float = 0f,
    val createdAt: Long = System.currentTimeMillis()
)
