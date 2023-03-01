package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.model.nft_contract.NormalizedMetadata
import com.swayy.home.domain.model.Contract
import kotlinx.coroutines.flow.Flow

interface ContractRepository {
    fun getContracts(address:String,limit:Int,normalizedMetadata: Boolean): Flow<Resource<List<Contract>>>
}