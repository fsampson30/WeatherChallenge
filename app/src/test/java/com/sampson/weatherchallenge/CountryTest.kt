package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.Country
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountryTest {

    private val country = Country

    @Test
    fun testCountryNameDefault(){
        val expected = "Brasil"
        val result = Country.getCountryDefault()
        Assert.assertEquals(result,expected)
    }

}