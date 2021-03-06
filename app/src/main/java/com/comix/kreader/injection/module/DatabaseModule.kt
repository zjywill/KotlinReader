package com.comix.kreader.injection.module

import android.arch.persistence.room.Room
import android.content.Context
import com.comix.kreader.model.database.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(context: Context): LocalDatabase = Room
            .databaseBuilder(context, LocalDatabase::class.java, "store.db")
            .build()

}