package com.example.converterapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.converterapp.databinding.ActivityExchangeRateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRateActivity : AppCompatActivity() {
    private val items = arrayOf("USD", "BYN", "RUB")
    private var input: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rate)

        val retrofit =
            Retrofit.Builder().baseUrl("https://api.nbrb.by").addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        val tv = findViewById<TextView>(R.id.tv)
        val b = findViewById<Button>(R.id.button)
        val api = retrofit.create(ApiService::class.java)
        b.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val rate = api.getRateById()
                runOnUiThread {
                    tv.text = rate.Cur_Abbreviation
                }
            }
        }


        val spinner = findViewById<Spinner>(R.id.spinnerFromCurrency)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Spinner", "Выбран элемент: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }

        }


    }
}
