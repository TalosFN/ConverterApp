package com.example.converterapp

import java.util.Date

data class ExchangeRate(
    val Cur_ID :Int,
    val Date :String,
    val Cur_Abbreviation :String,
    val Cur_Scale :Int,
    val Cur_Name :String,
    val Cur_OfficialRate :Double
)
