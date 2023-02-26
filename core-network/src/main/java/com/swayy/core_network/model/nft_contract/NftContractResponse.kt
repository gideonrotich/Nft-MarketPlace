package com.swayy.core_network.model.nft_contract

data class NftContractResponse(
    val cursor: String,
    val page: Int,
    val page_size: Int,
    val result: List<Result>,
    val total: Any
)