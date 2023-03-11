package com.swayy.core_network

import com.swayy.core_network.Constants.GET_EXCHANGE
import com.swayy.core_network.model.exchange.ExchangeResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockspanApi {

    @GET(GET_EXCHANGE)
    suspend fun getExchange(
        @Query("chain")chain:String,
        @Query("exchange")exchange:String
    ):ExchangeResponseDto
}