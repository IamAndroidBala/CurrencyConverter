package com.androidbala.currencyconverter.dagger

import com.androidbala.currencyconverter.network.ApiBuilder
import com.androidbala.currencyconverter.network.GetConversion
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyModule {

  @Provides
  @Singleton
  fun provideConversion(api       : ApiBuilder)   = GetConversion(api)

}