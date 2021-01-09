package com.androidbala.currencyconverter

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import com.androidbala.currencyconverter.model.CurrencyModel
import com.androidbala.currencyconverter.utils.AppLog
import com.androidbala.currencyconverter.utils.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_converter.*


class ConverterActivity : BaseActivity() {

    override fun contentView() = R.layout.activity_converter

    override fun init() {
        
        val currencyItems = Utility.getCurrencyNames(this@ConverterActivity)

        val currencyAdapter: ArrayAdapter<CurrencyModel> = ArrayAdapter<CurrencyModel>(this, android.R.layout.simple_spinner_item, currencyItems)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSelectCurrency.adapter = currencyAdapter

        spSelectCurrency.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val textView = spSelectCurrency.selectedView as TextView
                textView.text = currencyItems[position].name
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

}