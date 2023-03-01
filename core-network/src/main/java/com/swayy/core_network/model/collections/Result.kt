package com.swayy.core_network.model.collections

data class Result(
    val api_code: Int,
    val api_response: String,
    val data: List<Data>
)