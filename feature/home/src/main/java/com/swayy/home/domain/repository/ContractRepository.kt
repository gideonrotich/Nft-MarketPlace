package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.model.nft_contract.NormalizedMetadata
import com.swayy.home.domain.model.Contract

interface ContractRepository {
    suspend fun getContracts(address:String,normalizedMetadata: Boolean):Resource<List<Contract>>
}