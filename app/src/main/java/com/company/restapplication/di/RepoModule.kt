package com.company.restapplication.di

import com.company.restapplication.OurApplication
import com.company.restapplication.repo.WeatherRepoInterface
import com.company.restapplication.repo.WeatherRepoLocal
import com.company.restapplication.repo.WeatherRepoRemote
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun provideWeatherRepo(
        app: OurApplication,
        remote: WeatherRepoRemote,
        local: WeatherRepoLocal
    ) : WeatherRepoInterface {
        return if(app.isNetworkEnabled)
            remote
        else
            local
    }

}