package com.example.converterapp

import retrofit2.http.GET

interface ApiService {
    @GET("exrates/rates/431")
    suspend fun getRateById(): ExchangeRate
}