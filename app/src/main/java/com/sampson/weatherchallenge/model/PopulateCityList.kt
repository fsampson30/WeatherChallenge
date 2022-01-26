package com.sampson.weatherchallenge.model

class PopulateCityList {

    companion object {
        var cityList = mutableListOf<String>()
        fun populateList(): List<String> {
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
    }
}