package com.androidbala.currencyconverter.network

import com.androidbala.currencyconverter.model.CurrencyConvertingModel
import com.androidbala.currencyconverter.utils.API_KEY
import com.androidbala.currencyconverter.utils.EXCHANGE_URL
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * interface with api query and param values
 * here i have set EUR,GBP,CAD,PLN,INR,AUD,SGD,JPY as default currencies to be converted
 */
interface ApiInterface {

    @GET("live?access_key=$API_KEY&currencies=EUR,GBP,CAD,PLN,INR,AUD,SGD,JPY&source=&format=1")
    fun getConversion(@Query("source")mSource: String)  :  Call<CurrencyConvertingModel>

}