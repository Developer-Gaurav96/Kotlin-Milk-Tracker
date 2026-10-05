package com.example.milktracker.data.repository

import kotlinx.coroutines.flow.Flow
import com.example.milktracker.data.local.entity.Vendor

interface VendorRepository {
    fun allVendors(): Flow<List<Vendor>>
    suspend fun addVendor(vendor: Vendor): Long
    suspend fun updateVendor(vendor: Vendor)
    suspend fun deleteVendor(vendor: Vendor)
    suspend fun activeVendor(): Vendor?
    suspend fun getById(id: Long): Vendor?
}
