package com.androidbala.currencyconverter.utils

import android.content.Context
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.androidbala.currencyconverter.R
import com.androidbala.currencyconverter.model.CurrencyModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

/**
 * util functions
 */
object Utility {

    var selectedCurrency = "USD"

    /**
     * check internet connection available or not
     * this should called before make api call
     */
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context?): Boolean {

        if (context == null) return false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }

        return false

    }

    /**
     * this function used to read json file from asset folder
     */
    private fun getJsonDataFromAsset(mContext: Context): String? {
        val jsonString: String
        try {
            jsonString = mContext.assets.open(JSON_FILE_NAME).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getCurrencyNames(mContext: Context): List<CurrencyModel> {

        val jsonFileString = getJsonDataFromAsset(mContext)

        val gson = Gson()
        val listCurrency = object : TypeToken<List<CurrencyModel>>() {}.type

        return  gson.fromJson(jsonFileString, listCurrency)
    }

    /**
     * show toast message to user
     */
    @Suppress("DEPRECATION")
    fun showToast(string: String, context: Context?) {
        if (context != null) {

            val toast = Toast.makeText(context, string, Toast.LENGTH_SHORT)

            val view = toast.view
            view?.background?.setColorFilter(ContextCompat.getColor(context,R.color.colorPrimary), PorterDuff.Mode.SRC_IN)

            val text = view?.findViewById(android.R.id.message) as TextView
            text.setTextColor(ContextCompat.getColor(context,R.color.colorWhite))

            toast.show()

        }
    }

}