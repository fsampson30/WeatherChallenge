package com.sampson.weatherchallenge.model

class PopulateCountryList {

    companion object {
        var countryList = mutableListOf<String>()
        fun populateList() : List<String>{
            countryList.add("Lisboa")
            countryList.add("Madrid")
            countryList.add("Paris")
            countryList.add("Berlim")
            countryList.add("Copenhaga")
            countryList.add("Roma")
            countryList.add("Londres")
            countryList.add("Dublin")
            countryList.add("Praga")
            countryList.add("Viena")
            return countryList
        }
    }
}