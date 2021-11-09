package com.company.restapplication.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.restapplication.di.utils.DaggerAwareViewModelFactory
import com.company.restapplication.di.utils.ViewModelKey
import com.company.restapplication.view_models.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindViewModel(viewModel: WeatherViewModel): ViewModel

}