package com.example.converterapp

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("exrates/rates/{cur_id}")
    suspend fun getRateById(@Path("cur_id")cur_id:Int): ExchangeRate
}