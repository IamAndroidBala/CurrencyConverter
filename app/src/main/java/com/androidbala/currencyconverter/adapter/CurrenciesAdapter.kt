package com.androidbala.currencyconverter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidbala.currencyconverter.R
import com.androidbala.currencyconverter.model.ConversionModel
import com.androidbala.currencyconverter.utils.Utility
import kotlinx.android.synthetic.main.item_currencies.view.*

/**
 * adapter used to populate data in recyclerview
 */
class CurrenciesAdapter(var thisList: ArrayList<ConversionModel>) : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    var mBaseValue: Double = 0.0

    override fun onBindViewHolder(itemView: ViewHolder, position: Int) {
      itemView.bindItems(thisList[position])
    }

    override fun getItemCount() = thisList.size

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currencies, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(conversionModel: ConversionModel) {

             val temp = conversionModel.code.split(Utility.selectedCurrency).toTypedArray()

            if(temp.size==2){
                val header = "${Utility.selectedCurrency} to ${temp[1]}"
                itemView.tvCurrencyCode.text = header
            } else {
                itemView.tvCurrencyCode.text = conversionModel.code
            }

            conversionModel.rate?.let {
                val finalRate = mBaseValue * it
                itemView.tvCurrencyValue.text    = "$finalRate"
            }

        }

    }

    fun addResult(data : ArrayList<ConversionModel>, baseValue: String){
        mBaseValue = baseValue.toDouble()
        thisList.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData(){
        thisList.clear()
        notifyDataSetChanged()
    }

}