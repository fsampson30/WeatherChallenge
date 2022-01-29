package com.sampson.weatherchallenge.model

import com.sampson.weatherchallenge.DAO.EntityCity

class PopulateCityList {

    companion object {
        var cityList = mutableListOf<String>()
        fun populateList(): List<String> {
            cityList.clear()
            cityList.add("Lisboa")
            cityList.add("Madrid")
            cityList.add("Paris")
            cityList.add("Berlim")
            cityList.add("Copenhaga")
            cityList.add("Roma")
            cityList.add("Londres")
            cityList.add("Dublin")
            cityList.add("Praga")
            cityList.add("Viena")
            return cityList
        }

        fun getCityId(cityName: String): String {
            when (cityName) {
                "Lisboa" -> return "2267057"
                "Madrid" -> return "3117735"
                "Paris" -> return "2968815"
                "Berlim" -> return "2950157"
                "Copenhaga" -> return "2618425"
                "Roma" -> return "3169070"
                "Londres" -> return "2643741"
                "Dublin" -> return "2964574"
                "Praga" -> return "3067696"
                "Viena" -> return "2761369"
            }
            return ""
        }

        fun populateCitiesDatabase() : MutableList<EntityCity>{
            val cities =  mutableListOf<EntityCity>()
            cities.add(EntityCity(0,"Lisboa","2267057"))
            cities.add(EntityCity(0,"Madrid","3117735"))
            cities.add(EntityCity(0,"Paris","2968815"))
            cities.add(EntityCity(0,"Berlim","2950157"))
            cities.add(EntityCity(0,"Copenhaga","2618425"))
            cities.add(EntityCity(0,"Roma","3169070"))
            cities.add(EntityCity(0,"Londres","2643741"))
            cities.add(EntityCity(0,"Dublin","2964574"))
            cities.add(EntityCity(0,"Praga","3067696"))
            cities.add(EntityCity(0,"Viena","2761369"))
            return cities
        }
    }

}