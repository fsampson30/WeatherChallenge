package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.Country
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CountryTestParameterized(
    private val input: String,
    private val expected: String
) {
    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun getData(): List<Array<out Any>> = listOf(
            arrayOf("PT", "Portugal"),
            arrayOf("ES", "Espanha"),
            arrayOf("FR", "França"),
            arrayOf("DE", "Alemanha"),
            arrayOf("DK", "Dinamarca"),
            arrayOf("IT", "Itália"),
            arrayOf("GB", "Reino Unido"),
            arrayOf("IE", "Irlanda"),
            arrayOf("CZ", "Tchéquia"),
            arrayOf("AT", "Áustria")
        )
    }

    private val country = Country
    @Test
    fun defaultCountry(){
        val result = country.getCountryName(input)
        Assert.assertEquals(result,expected)
    }
}
