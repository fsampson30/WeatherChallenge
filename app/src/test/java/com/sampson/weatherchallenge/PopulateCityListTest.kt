package com.sampson.weatherchallenge

import com.sampson.weatherchallenge.model.PopulateCityList
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)


class PopulateCityListTest {

    @Test
    fun listSize() {
        val populate = PopulateCityList
        val size = 10
        val expected = populate.populateCitiesDatabase().size
        assertEquals(size,expected)
    }


}

