package com.swayy.core_network.model.collections.nft

data class NftResponseDto(
    val chain: String,
    val cursor: String,
    val per_page: Int,
    val results: List<Result>,
    val total: Int
)