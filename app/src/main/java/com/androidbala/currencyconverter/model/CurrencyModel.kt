package com.androidbala.currencyconverter.model

data class CurrencyModel(var code: String, var name: String){
    override fun toString(): String {
        return name
    }
}