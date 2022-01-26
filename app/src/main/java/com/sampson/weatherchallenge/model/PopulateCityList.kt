package com.sampson.weatherchallenge.model

class PopulateCityList {

    companion object {
        var cityList = mutableListOf<String>()
        fun populateList() : List<String>{
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
    }
}