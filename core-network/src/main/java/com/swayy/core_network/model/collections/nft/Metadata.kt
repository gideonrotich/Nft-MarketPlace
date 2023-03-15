package com.swayy.core_network.model.collections.nft

data class Metadata(
    val attributes: List<Attribute>,
    val edition: Int,
    val external_url: String,
    val image: String,
    val symbol: String
)