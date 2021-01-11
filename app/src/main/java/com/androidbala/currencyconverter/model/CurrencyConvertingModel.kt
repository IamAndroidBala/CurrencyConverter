package com.androidbala.currencyconverter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyConvertingModel(
    var success: Boolean = true,
    var terms: String,
    var privacy: String,
    var timestamp: Long ,
    var source: String,
    var quotes:Map<String, Double>,
    var error: ErrorModel) : Parcelable {}

@Parcelize
data class ErrorModel(var code: Int,
                      var info: String) : Parcelable{}
