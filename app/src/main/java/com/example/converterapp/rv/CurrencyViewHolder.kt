package com.example.converterapp.rv

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.R

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateTextView: TextView = itemView.findViewById(R.id.tv_Date)
    private val scaleTextView: TextView = itemView.findViewById(R.id.tv_Scale)
    private val nameTextView: TextView = itemView.findViewById(R.id.tv_Name)
    private val abrTextView: TextView = itemView.findViewById(R.id.tv_Abr)
    private val rateTextView: TextView = itemView.findViewById(R.id.tv_Rate)

    fun bind(currency: Curency) {
        dateTextView.text = "Дата: ${currency.date}"
        scaleTextView.text = "Масштаб: ${currency.scale}"
        nameTextView.text = "Название: ${currency.name}"
        abrTextView.text = "Аббривиатура: ${currency.abr}"
        rateTextView.text = "Курс: ${currency.rate}"
    }
}