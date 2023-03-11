package com.swayy.core_network.model.exchange

data class Stats(
    val floor_price: Int,
    val market_cap: Double,
    val num_owners: Int,
    val one_day_average_price: Double,
    val one_day_sales: Int,
    val one_day_volume: Double,
    val one_day_volume_change: Double,
    val seven_day_average_price: Double,
    val seven_day_sales: Int,
    val seven_day_volume: Double,
    val seven_day_volume_change: Double,
    val thirty_day_average_price: Double,
    val thirty_day_sales: Int,
    val thirty_day_volume: Double,
    val thirty_day_volume_change: Double,
    val total_average_price: Double,
    val total_minted: Int,
    val total_sales: Int,
    val total_supply: Int,
    val total_volume: Double
)