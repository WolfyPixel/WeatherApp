package com.company.restapplication.view_models

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.company.restapplication.OurApplication
import com.company.restapplication.db.models.Weather
import com.company.restapplication.repo.WeatherRepoInterface
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    app: OurApplication,
    private val repo: WeatherRepoInterface
) : AndroidViewModel(app) {

    sealed class Event {
        class OnError(val msg: String) : Event()
        class OnItemSelected(val item: Weather) : Event()
    }

    var searchText: String = ""
        set(value) {
            field = value

            if(field.length > 3)
                getItems()
        }

    val event = MutableLiveData<Event>()
    val items = MutableLiveData<ArrayList<Weather>>(arrayListOf())
    val isLoading = MutableLiveData<Boolean>(false)

    fun getItems(){
        isLoading.value = true
        viewModelScope.launch {
            val weatherList = repo.getWeather(searchText)

            items.value?.addAll(weatherList)
            items.value = items.value
            isLoading.value = false
        }
    }

    fun selectItem(item: Weather){
        event.value = Event.OnItemSelected(item)
    }

}