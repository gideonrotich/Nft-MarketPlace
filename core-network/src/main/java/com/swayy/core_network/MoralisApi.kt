package com.swayy.core_network

import com.swayy.core_network.Constants.GET_NFT_CONTRACT
import com.swayy.core_network.model.nft_contract.NftContractResponse
import retrofit2.http.*

interface MoralisApi {
    @Headers("X-API-Key: jRx8UBaOaneHQuOvz0ONlAitJYnEdcWcSWf5NRgoevLkFDhXDS9hwgkHgs2csq7E")
    @GET(GET_NFT_CONTRACT)
    suspend fun getContract(
        @Path("address")address:String,
        @Query("normalizeMetadata")normalizeMetadata: Boolean
    ):NftContractResponse
}