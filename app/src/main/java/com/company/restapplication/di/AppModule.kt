package com.company.restapplication.di

import android.content.Context
import com.company.restapplication.OurApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun getContext(application: OurApplication) : Context{
        return application.applicationContext
    }

}