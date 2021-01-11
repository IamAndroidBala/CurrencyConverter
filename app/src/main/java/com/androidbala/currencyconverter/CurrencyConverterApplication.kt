package com.androidbala.currencyconverter

import android.app.Application
import com.androidbala.currencyconverter.dagger.AppComponent
import com.androidbala.currencyconverter.dagger.AppModule
import com.androidbala.currencyconverter.dagger.DaggerAppComponent

@Suppress("DEPRECATION")
class CurrencyConverterApplication: Application() {

    lateinit var currencyComponent : AppComponent

    override fun onCreate() {
        super.onCreate()

        currencyComponent = initDagger(this)

    }

    /**
     * initialise dagger
     */
    private fun initDagger(app: CurrencyConverterApplication): AppComponent = DaggerAppComponent.builder().appModule(AppModule(app)).build()

}