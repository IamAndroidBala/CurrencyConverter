package com.androidbala.currencyconverter

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.androidbala.currencyconverter.utils.Utility

/**
 * Launch screen for this app
 * it should wait for a minute
 */
class SplashScreenActivity : AppCompatActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, ConverterActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK })
            finish()
        },1000)

    }

}