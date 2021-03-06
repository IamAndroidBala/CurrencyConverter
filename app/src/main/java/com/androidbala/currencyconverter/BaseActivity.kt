package com.androidbala.currencyconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * base activity for app
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun contentView() : Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(contentView())

        init()

    }

}