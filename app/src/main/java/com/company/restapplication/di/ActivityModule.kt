package com.company.restapplication.di

import com.company.restapplication.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity() : MainActivity

}