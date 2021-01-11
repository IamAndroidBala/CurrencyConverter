package com.androidbala.currencyconverter.ui

import com.androidbala.currencyconverter.model.CurrencyConvertingModel
import com.androidbala.currencyconverter.network.ApiInterface
import com.androidbala.currencyconverter.network.GetConversion
import com.androidbala.currencyconverter.utils.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * making api here and return result
 */
class CurrencyPresenter @Inject constructor(private val cRate: GetConversion) : CurrencyPresenterImpl {

    private lateinit var currencyView: CurrencyView

    override fun setPage(mCurrencyView: CurrencyView) {
        this.currencyView = mCurrencyView
    }

    override fun setLoading() {

        currencyView.displayLoading()

        cRate.getData().create(ApiInterface::class.java).getConversion(Utility.selectedCurrency).enqueue(object : Callback<CurrencyConvertingModel>{
            override fun onResponse(call: Call<CurrencyConvertingModel>, response: Response<CurrencyConvertingModel>) {

              response.body()?.let {
                  if(it.success)
                      currencyView.displayResult(it)
                  else
                      currencyView.displayError(it.error.info)
              }

            }

            override fun onFailure(call: Call<CurrencyConvertingModel>, t: Throwable) {
                currencyView.displayError(t.localizedMessage)
            }
        })
    }

}