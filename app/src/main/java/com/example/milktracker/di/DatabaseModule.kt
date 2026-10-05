package com.example.milktracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.milktracker.data.local.AppDatabase
import com.example.milktracker.data.local.dao.EntryDao
import com.example.milktracker.data.local.dao.VendorDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideEntryDao(db: AppDatabase): EntryDao = db.entryDao()

    @Provides
    @Singleton
    fun provideVendorDao(db: AppDatabase): VendorDao = db.vendorDao()
}
