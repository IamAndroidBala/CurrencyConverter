package com.androidbala.currencyconverter.model

data class CurrencyModel(var code: String, var name: String){
    override fun toString(): String {
        return name
    }
}

data class ConversionModel(var code: String, var rate: Double?){

}

