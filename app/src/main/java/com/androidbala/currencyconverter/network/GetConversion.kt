package com.androidbala.currencyconverter.network


class GetConversion (private val apiBuilder: ApiBuilder) {

    fun getData() = apiBuilder.getConversion()

}