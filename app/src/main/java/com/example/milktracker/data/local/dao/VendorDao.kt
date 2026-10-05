package com.example.milktracker.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.milktracker.data.local.entity.Vendor

@Dao
interface VendorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vendor: Vendor): Long

    @Update
    suspend fun update(vendor: Vendor)

    @Delete
    suspend fun delete(vendor: Vendor)

    @Query("SELECT * FROM vendors ORDER BY isActive DESC, name ASC")
    fun allVendors(): Flow<List<Vendor>>

    @Query("SELECT * FROM vendors WHERE isActive = 1 LIMIT 1")
    suspend fun activeVendor(): Vendor?

    @Query("SELECT * FROM vendors WHERE id = :id")
    suspend fun getById(id: Long): Vendor?
}
