package com.example.listofcountries.ui.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.listofcountries.data.errorhandling.ResultOf
import com.example.listofcountries.data.model.CountriesModel
import com.example.listofcountries.data.model.CountriesModelItemModel
import com.example.listofcountries.data.remote.ApiRequest
import com.example.listofcountries.ui.errorhandling.Errorhandling.doIfSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.IOException

class CountriesViewModelTest{

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiRequest: ApiRequest


    @Before
    fun setUP() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testGetCountries_emptyResponse()= runTest {
        val countries=CountriesModel()

        Mockito.`when`(apiRequest.getCountries()).thenReturn(countries)
        val viewModel=CountriesViewModel(apiRequest)
        viewModel.getCountries()

        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        viewModel.countries.value?.doIfSuccess {  assertEquals(0,it.size)}

    }

    @Test
    fun `getCountries with vaild response`()= runTest {
        val countries= listOf(CountriesModelItemModel(
            name="india",
            region = "IN",
            code = "91",
            capital = "Indian"),
            CountriesModelItemModel(
                name="United Kingdom",
                region = "UK",
                code = "44",
                capital = "London"
            ))
        val countries_sample=CountriesModel()
        countries_sample.addAll(countries)
        Mockito.`when`(apiRequest.getCountries()).thenReturn(countries_sample)
        val viewModel=CountriesViewModel(apiRequest)
        viewModel.getCountries()

        testDispatcher.scheduler.advanceUntilIdle()

     viewModel.countries.value?.doIfSuccess {  assertEquals(2,it.size)}

    }

}

