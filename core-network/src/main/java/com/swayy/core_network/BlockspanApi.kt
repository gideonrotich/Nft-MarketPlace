package com.swayy.core_network

import com.swayy.core_network.Constants.GET_EXCHANGE
import com.swayy.core_network.Constants.GET_RANKING
import com.swayy.core_network.Constants.GET_SINGLE
import com.swayy.core_network.model.exchange.ExchangeResponseDto
import com.swayy.core_network.model.exchange.ranking.RankingResponseDto
import com.swayy.core_network.model.exchange.single.SingleResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface BlockspanApi {

    @Headers("X-API-Key: xcxL3trFzDp79zCScbpXJzijgQBWJzZu")
    @GET(GET_EXCHANGE)
    suspend fun getExchange(
        @Query("chain") chain: String,
        @Query("exchange") exchange: String
    ): ExchangeResponseDto

    @Headers("X-API-Key: xcxL3trFzDp79zCScbpXJzijgQBWJzZu")
    @GET(GET_RANKING)
    suspend fun getRanking(
        @Query("chain") chain: String,
        @Query("exchange") exchange: String,
        @Query("ranking") ranking: String
    ): RankingResponseDto

    @Headers("X-API-Key: xcxL3trFzDp79zCScbpXJzijgQBWJzZu")
    @GET(GET_SINGLE)
    suspend fun getSingle(
        @Path("key") key: String,
        @Query("chain") chain: String,
        @Query("exchange") exchange: String
    ): SingleResponseDto
}