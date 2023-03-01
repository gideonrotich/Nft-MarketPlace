package com.swayy.home.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.home.domain.model.Contract
import com.swayy.home.domain.repository.ContractRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetContractUseCase @Inject constructor(
    private val repository: ContractRepository
) {
    operator fun invoke(
        address: String,
        limit:Int,
        normalizedMetadata: Boolean
    ): Flow<Resource<List<Contract>>> {
        return repository.getContracts(address, limit,normalizedMetadata)
    }
}