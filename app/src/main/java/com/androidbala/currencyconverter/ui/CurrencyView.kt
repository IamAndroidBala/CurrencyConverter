package com.androidbala.currencyconverter.ui

import com.androidbala.currencyconverter.model.CurrencyConvertingModel

/**
 * interface used to handle UI
 */
interface CurrencyView {

    fun displayLoading()

    fun dismissLoading()

    fun displayResult(result : CurrencyConvertingModel)

    fun displayError(error : String?)

}