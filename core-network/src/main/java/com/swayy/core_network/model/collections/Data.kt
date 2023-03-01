package com.swayy.core_network.model.collections

data class Data(
    val floor: Double,
    val floor_marketcap: Double,
    val floor_marketcap_pretty: String,
    val holders: Int,
    val items: Int,
    val logo: String,
    val me_key: String,
    val metadata_refresh_ts: Int,
    val name: String,
    val official_rarity: Int,
    val on_sale: Int,
    val url: String
)