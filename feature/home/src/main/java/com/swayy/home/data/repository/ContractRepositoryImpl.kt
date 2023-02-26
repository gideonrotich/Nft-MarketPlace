package com.swayy.home.data.repository

import com.swayy.core.util.Resource
import com.swayy.core.util.safeApiCall
import com.swayy.core_network.MoralisApi
import com.swayy.core_network.model.nft_contract.NormalizedMetadata
import com.swayy.home.data.toData
import com.swayy.home.domain.model.Contract
import com.swayy.home.domain.repository.ContractRepository
import kotlinx.coroutines.Dispatchers

class ContractRepositoryImpl(
    private val moralisApi: MoralisApi
) : ContractRepository {
    override suspend fun getContracts(
        address: String,
        normalizedMetadata: Boolean
    ): Resource<List<Contract>> {
        return safeApiCall(Dispatchers.IO) {
            val response =
                moralisApi.getContract(address = address, normalizeMetadata = normalizedMetadata)
            response.result.map { it.toData() }
        }
    }
}