package com.swayy.core_network.model.collection_details

data class Item(
    val all_ranks: AllRanks,
    val attributes: List<Attribute>,
    val description: String,
    val id: Int,
    val image: String,
    val link: String,
    val mint: String,
    val name: String,
    val rank: Int,
    val rank_algo: String
)