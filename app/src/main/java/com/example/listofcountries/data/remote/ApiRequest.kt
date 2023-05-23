package com.example.listofcountries.data.remote

import com.example.listofcountries.data.model.CountriesModel
import retrofit2.http.GET

interface ApiRequest {

    @GET(ApiDetails.Countries)
    suspend fun getCountries(): CountriesModel
}