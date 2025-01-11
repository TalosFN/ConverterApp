package com.example.converterapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
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
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRateActivity : AppCompatActivity() {
    private val items = arrayOf("USD", "RUB", "EUR", "JPY")
    private var input: Int = 0
    var selectedItem: String? = null


    @SuppressLint("SetTextI18n")
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
        suspend fun calc(id: Int, amount: Double): Double {
            val rate = api.getRateById(id)
            val sum = rate.Cur_OfficialRate * amount / rate.Cur_Scale
            return sum
        }

        val spinner = findViewById<Spinner>(R.id.spinnerFromCurrency)
        val adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = parent.getItemAtPosition(position).toString()
                Log.d("Spinner", "Выбран элемент: $selectedItem")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedItem = null
            }

        }
        b.setOnClickListener {
            when (selectedItem) {
                "USD" -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val cur = findViewById<EditText>(R.id.editTextAmount)
                        val tv = findViewById<TextView>(R.id.tv)
                        val result = calc(431, cur.text.toString().toDouble())
                        runOnUiThread {
                            tv.text = result.toString()
                        }
                    }
                }

                "RUB" -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val cur = findViewById<EditText>(R.id.editTextAmount)
                        val tv = findViewById<TextView>(R.id.tv)
                        val result = calc(456, cur.text.toString().toDouble())
                        runOnUiThread {
                            tv.text = result.toString()
                        }
                    }
                }

                "EUR" -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val cur = findViewById<EditText>(R.id.editTextAmount)
                        val tv = findViewById<TextView>(R.id.tv)
                        val result = calc(451, cur.text.toString().toDouble())
                        runOnUiThread {
                            tv.text = result.toString()
                        }
                    }
                }

                else -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        val cur = findViewById<EditText>(R.id.editTextAmount)
                        val tv = findViewById<TextView>(R.id.tv)
                        val result = calc(508, cur.text.toString().toDouble())
                        runOnUiThread {
                            tv.text = result.toString()
                        }
                    }
                }
            }
        }



    }
}
