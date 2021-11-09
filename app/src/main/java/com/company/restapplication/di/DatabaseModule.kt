package com.company.restapplication.di

import android.content.Context
import androidx.room.Room
import com.company.restapplication.BuildConfig
import com.company.restapplication.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.APPLICATION_ID)
            .fallbackToDestructiveMigration()
            .build()
    }

}