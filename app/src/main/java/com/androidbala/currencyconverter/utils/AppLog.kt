package com.androidbala.currencyconverter.utils

import android.util.Log

object AppLog {

    fun d(message: String?){
        if(IS_TEST_BUILD)
            message?.let {
                Log.d("Converter=>" , it)
            }
    }

}