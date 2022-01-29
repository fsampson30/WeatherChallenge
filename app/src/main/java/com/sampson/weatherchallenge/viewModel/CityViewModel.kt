package com.sampson.weatherchallenge.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.sampson.weatherchallenge.DAO.CityRepository
import com.sampson.weatherchallenge.DAO.EntityCity

class CityViewModel (private val repository: CityRepository) : ViewModel(){

    val allCities: LiveData<MutableList<EntityCity>> = repository.allCities.asLiveData()
}

class CitiViewModelFactory(private val repository: CityRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}