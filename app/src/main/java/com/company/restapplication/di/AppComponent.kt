package com.company.restapplication.di

import com.company.restapplication.OurApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class, AppModule::class,
    DatabaseModule::class, NetworkModule::class,
    ViewModelModule::class, ActivityModule::class,
    RepoModule::class
])
interface AppComponent : AndroidInjector<OurApplication> {

    @Component.Factory
    abstract class Factory: AndroidInjector.Factory<OurApplication>

}