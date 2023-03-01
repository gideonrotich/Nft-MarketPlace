package com.swayy.core_network

import com.swayy.core_network.model.collections.CollectionResponseDto
import com.swayy.core_network.model.nft_contract.NftContractResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HowrareApi {
    @GET(Constants.GET_COLLECTIONS)
    suspend fun getCollections(): CollectionResponseDto
}