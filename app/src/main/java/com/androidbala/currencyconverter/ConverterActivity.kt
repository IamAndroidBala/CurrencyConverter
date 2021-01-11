package com.androidbala.currencyconverter

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.androidbala.currencyconverter.model.ConversionModel
import com.androidbala.currencyconverter.model.CurrencyConvertingModel
import com.androidbala.currencyconverter.model.CurrencyModel
import com.androidbala.currencyconverter.adapter.CurrenciesAdapter
import com.androidbala.currencyconverter.ui.CurrencyPresenterImpl
import com.androidbala.currencyconverter.ui.CurrencyView
import com.androidbala.currencyconverter.utils.Utility
import com.androidbala.currencyconverter.utils.errorDialog
import kotlinx.android.synthetic.main.activity_converter.*
import javax.inject.Inject

/**
 * Main screen
 * here user can enter currency value and select the desire currency to convert
 * Basic Plan API KEY has created in https://currencylayer.com
 * this api key should support to limited currency conversion only
 */
class ConverterActivity : BaseActivity(), CurrencyView {

    private lateinit var cAdapter : CurrenciesAdapter
    private var cList = ArrayList<ConversionModel>()
    @Inject
    lateinit var myActivityPresenter : CurrencyPresenterImpl

    override fun contentView() = R.layout.activity_converter

    override fun init() {

        /**
         * set up recyclerview with layout manager
         */
        recyclerCurrency.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this@ConverterActivity,2)
        recyclerCurrency.layoutManager = layoutManager

        /**
         * set up adapter for recyclerview
         */
        cAdapter = CurrenciesAdapter(cList)
        recyclerCurrency.adapter = cAdapter

        /**
         * inject dagger component here
         */
        (application as CurrencyConverterApplication).currencyComponent.inject(this)

        /**
         * get currencies.json from asset and display in spinner
         */
        val currencyItems = Utility.getCurrencyNames(this@ConverterActivity)

        val currencyAdapter: ArrayAdapter<CurrencyModel> = ArrayAdapter<CurrencyModel>(this, android.R.layout.simple_spinner_item, currencyItems)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSelectCurrency.adapter = currencyAdapter

        /**
         * set USD as default selected
         */
        spSelectCurrency.post{ spSelectCurrency.setSelection(currencyItems.indexOf(CurrencyModel("USD","United States Dollar"))) }

        /**
         * read user selected value
         */
        spSelectCurrency.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                val textView  = spSelectCurrency.selectedView as TextView
                textView.text = currencyItems[position].code

                Utility.selectedCurrency = currencyItems[position].code

                if(!Utility.isNetworkAvailable(this@ConverterActivity)) {
                    Utility.showToast(getString(R.string.no_internet),applicationContext)
                    return
                }

                if(!TextUtils.isEmpty(edCurrencyValue.text.toString().trim())){
                    cAdapter.clearData()
                    myActivityPresenter.setPage(this@ConverterActivity)
                    myActivityPresenter.setLoading()
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /**
         * watch what is user entering on editext
         */
        edCurrencyValue.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                cAdapter.clearData()
                myActivityPresenter.setPage(this@ConverterActivity)
                myActivityPresenter.setLoading()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }

    override fun displayLoading() {
        this.wait_progress_bar.post {
            this.wait_progress_bar.visibility = View.GONE
            this.recyclerCurrency.visibility = View.VISIBLE
        }
    }

    override fun dismissLoading() {
        this.wait_progress_bar.post {
            this.wait_progress_bar.visibility = View.GONE
            this.recyclerCurrency.visibility = View.VISIBLE
        }
    }

    /**
     * populate the result in UI
     */
    override fun displayResult(result : CurrencyConvertingModel) {
        this.recyclerCurrency.post {
            val tempArrayList = ArrayList<ConversionModel>()
            for ((key, value) in result.quotes) {
                tempArrayList.add(ConversionModel(key, value))
            }

            if (!TextUtils.isEmpty(edCurrencyValue.text.toString().trim())) {
                cAdapter.addResult(tempArrayList, edCurrencyValue.text.toString())
            }
        }
    }

    /**
     * display the error message to user from api
     */
    override fun displayError(error : String?) {
        runOnUiThread {
            error?.errorDialog(this@ConverterActivity)
        }
    }

}


