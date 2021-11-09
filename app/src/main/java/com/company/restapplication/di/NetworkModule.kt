package com.company.restapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.company.restapplication.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideGson() : Gson{
        return GsonBuilder()
            .setLenient()
            .create()
    }

}