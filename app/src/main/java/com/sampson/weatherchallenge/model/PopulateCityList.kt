package com.sampson.weatherchallenge.model

import com.sampson.weatherchallenge.DAO.EntityCity

class PopulateCityList {

    companion object {

        fun populateCitiesDatabase(): MutableList<EntityCity> {
            val cities = mutableListOf<EntityCity>()
            cities.add(EntityCity(0, "Lisboa", "2267057"))
            cities.add(EntityCity(0, "Madrid", "3117735"))
            cities.add(EntityCity(0, "Paris", "2968815"))
            cities.add(EntityCity(0, "Berlim", "2950157"))
            cities.add(EntityCity(0, "Copenhaga", "2618425"))
            cities.add(EntityCity(0, "Roma", "3169070"))
            cities.add(EntityCity(0, "Londres", "2643741"))
            cities.add(EntityCity(0, "Dublin", "2964574"))
            cities.add(EntityCity(0, "Praga", "3067696"))
            cities.add(EntityCity(0, "Viena", "2761369"))
            return cities
        }
    }

}