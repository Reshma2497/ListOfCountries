package com.example.listofcountries.data.remote

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {


    fun getRetrofitInstance():Retrofit
    {
        val gson= Gson()
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()



    }


}