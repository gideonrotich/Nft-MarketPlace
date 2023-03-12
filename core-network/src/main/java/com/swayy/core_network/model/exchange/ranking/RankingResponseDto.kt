package com.swayy.core_network.model.exchange.ranking

data class RankingResponseDto(
    val chain: String,
    val cursor: String,
    val exchange: String,
    val per_page: Int,
    val ranking: String,
    val results: List<Result>,
    val total: Int
)