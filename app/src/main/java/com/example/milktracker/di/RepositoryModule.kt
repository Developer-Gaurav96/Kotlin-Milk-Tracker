package com.example.milktracker.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.milktracker.data.repository.EntryRepository
import com.example.milktracker.data.repository.EntryRepositoryImpl
import com.example.milktracker.data.repository.VendorRepository
import com.example.milktracker.data.repository.VendorRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindEntryRepo(impl: EntryRepositoryImpl): EntryRepository

    @Binds
    @Singleton
    abstract fun bindVendorRepo(impl: VendorRepositoryImpl): VendorRepository
}
