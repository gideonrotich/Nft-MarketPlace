package com.swayy.core_network.model.exchange

data class Result(
    val banner_image_url: String,
    val chat_url: Any,
    val contracts: List<Contract>,
    val description: String,
    val discord_url: String,
    val exchange: String,
    val exchange_url: String,
    val external_url: String,
    val featured_image_url: String,
    val image_url: String,
    val instagram_username: String,
    val key: String,
    val large_image_url: String,
    val name: String,
    val stats: Stats,
    val telegram_url: Any,
    val twitter_username: String,
    val update_at: String,
    val wiki_url: Any
)