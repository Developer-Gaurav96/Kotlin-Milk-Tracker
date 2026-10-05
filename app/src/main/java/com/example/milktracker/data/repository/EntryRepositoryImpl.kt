package com.example.milktracker.data.repository

import kotlinx.coroutines.flow.Flow
import com.example.milktracker.data.local.entity.Entry
import com.example.milktracker.data.local.entity.Vendor
import com.example.milktracker.data.local.dao.EntryDao
import com.example.milktracker.data.local.dao.VendorDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntryRepositoryImpl @Inject constructor(
    private val entryDao: EntryDao,
    private val vendorDao: VendorDao
) : EntryRepository {
    override suspend fun addEntry(entry: Entry): Long {
        return entryDao.insert(entry)
    }

    override fun entriesInMonth(startEpoch: Long, endEpoch: Long): Flow<List<Entry>> =
        entryDao.entriesInRange(startEpoch, endEpoch)

    override fun entriesForDay(dayEpoch: Long): Flow<List<Entry>> =
        entryDao.entriesForDay(dayEpoch)

    override suspend fun getShiftsForDay(dayEpoch: Long): List<String> =
        entryDao.shiftsForDay(dayEpoch)

    override suspend fun deleteEntry(entry: Entry) = entryDao.delete(entry)

    override fun totalQuantity(start: Long, end: Long): Flow<Float?> =
        entryDao.totalQuantityInRange(start, end)

    override fun totalBill(start: Long, end: Long): Flow<Float?> =
        entryDao.totalBillInRange(start, end)

    override fun daysLogged(start: Long, end: Long): Flow<Int?> =
        entryDao.daysLoggedInRange(start, end)
}
