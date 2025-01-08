package com.example.converterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.converterapp.rv.Curency
import com.example.converterapp.rv.CurrencyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val recyclerView: RecyclerView = findViewById(R.id.rvCurrencyList)
        val retrofit =
            Retrofit.Builder().baseUrl("https://api.nbrb.by").addConverterFactory(
                GsonConverterFactory.create()
            ).build()
        val api = retrofit.create(ApiService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val USDrate = api.getRateById(431)
            val RUBrate = api.getRateById(456)
            val EURrate = api.getRateById(451)
            val JPYrate = api.getRateById(508)

            val currencies = listOf(
                Curency(
                    USDrate.Date,
                    USDrate.Cur_Scale,
                    USDrate.Cur_Name,
                    USDrate.Cur_Abbreviation,
                    USDrate.Cur_OfficialRate
                ),
                Curency(
                    RUBrate.Date,
                    RUBrate.Cur_Scale,
                    RUBrate.Cur_Name,
                    RUBrate.Cur_Abbreviation,
                    RUBrate.Cur_OfficialRate
                ),
                Curency(
                    EURrate.Date,
                    EURrate.Cur_Scale,
                    EURrate.Cur_Name,
                    EURrate.Cur_Abbreviation,
                    EURrate.Cur_OfficialRate
                ),
                Curency(
                    JPYrate.Date,
                    JPYrate.Cur_Scale,
                    JPYrate.Cur_Name,
                    JPYrate.Cur_Abbreviation,
                    JPYrate.Cur_OfficialRate
                )
            )

            runOnUiThread {
                val adapter = CurrencyAdapter(currencies)
                recyclerView.adapter = adapter
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
