package com.swayy.ranking.domain.model

import com.swayy.core_network.model.exchange.ranking.Contract

data class Ranking(
    val contracts: List<Contract>,
    val description: String,
    val key: String,
    val name: String,
    val one_day_average_price: String,
    val one_day_sales: String,
    val one_day_volume: String,
    val seven_day_average_price: String,
    val seven_day_sales: String,
    val seven_day_volume: String,
    val thirty_day_average_price: String,
    val thirty_day_sales: String,
    val thirty_day_volume: String,
    val total_average_price: String,
    val total_sales: String,
    val total_volume: String,
    val update_at: String
)
