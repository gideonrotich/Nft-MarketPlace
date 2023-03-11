package com.swayy.core_network.model.exchange

data class ExchangeResponseDto(
    val chain: String,
    val cursor: String,
    val exchange: String,
    val per_page: Int,
    val results: List<Result>,
    val total: Int
)