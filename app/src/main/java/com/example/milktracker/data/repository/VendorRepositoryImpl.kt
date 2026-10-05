package com.example.milktracker.data.repository

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import com.example.milktracker.data.local.entity.Vendor
import com.example.milktracker.data.local.dao.VendorDao

@Singleton
class VendorRepositoryImpl @Inject constructor(
    private val vendorDao: VendorDao
) : VendorRepository {
    override fun allVendors(): Flow<List<Vendor>> = vendorDao.allVendors()

    override suspend fun addVendor(vendor: Vendor): Long = vendorDao.insert(vendor)

    override suspend fun updateVendor(vendor: Vendor) = vendorDao.update(vendor)

    override suspend fun deleteVendor(vendor: Vendor) = vendorDao.delete(vendor)

    override suspend fun activeVendor(): Vendor? = vendorDao.activeVendor()

    override suspend fun getById(id: Long): Vendor? = vendorDao.getById(id)
}
