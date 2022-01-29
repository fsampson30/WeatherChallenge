package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.Country
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CountryTest {

    private val country = Country

    @Test()
    fun testCountryNameDefault(){
        val expected = "Brasil"
        val result = Country.getCountryDefault()
        Assert.assertEquals(result,expected)
    }

    @Test(expected = Country.InvalidStringException::class)
    fun testCountryNameEmptyInput(){
        val input = ""
        val result = Country.getCountryName(input)
        Assert.assertEquals(result, "")
    }

    @Test(expected = Country.InvalidStringException::class)
    fun testCountryNameWrongInput(){
        val input = "AAA"
        val result = Country.getCountryName(input)
        Assert.assertEquals(result, "")
    }

}