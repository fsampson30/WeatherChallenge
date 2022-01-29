package com.sampson.weatherchallenge.viewModel

import androidx.lifecycle.*
import com.sampson.weatherchallenge.DAO.CityRepository
import com.sampson.weatherchallenge.DAO.EntityCityPartials
import kotlinx.coroutines.launch

class CityWeatherViewModel(private val repository: CityRepository) : ViewModel() {

    val allPartials: LiveData<MutableList<EntityCityPartials>> = repository.allPartials.asLiveData()

    fun insert(partial: EntityCityPartials) = viewModelScope.launch {
        repository.insertPartial(partial)
    }
}

class CitiWeatherViewModelFactory(private val repository: CityRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityWeatherViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CityWeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}