package com.example.listofcountries.ui.countries

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listofcountries.data.errorhandling.ResultOf
import com.example.listofcountries.data.model.CountriesModel
import com.example.listofcountries.data.remote.ApiRequest
import com.example.listofcountries.data.remote.RetrofitHelper
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CountriesViewModel(apiRequest: ApiRequest) : ViewModel() {

    val countries=MutableLiveData< ResultOf<CountriesModel>>()

    fun getCountries()
    {
        viewModelScope.launch {
            try{
            val retrofit=RetrofitHelper.getRetrofitInstance().create(ApiRequest::class.java)
            val result=retrofit.getCountries()
                countries.postValue(ResultOf.Success(result))
            }catch (ioe: IOException){
                countries.postValue(ResultOf.Failure("[IO] error please retry", ioe))
            }catch (he: HttpException){
                countries.postValue(ResultOf.Failure("[HTTP] error please retry", he))
            }

        }
    }

}