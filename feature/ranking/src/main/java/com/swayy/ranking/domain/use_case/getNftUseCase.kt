package com.swayy.ranking.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Nft
import com.swayy.ranking.domain.repository.NftRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getNftUseCase @Inject constructor(
    private val repository: NftRepository
) {
    operator fun invoke(contract_address:String,chain:String):Flow<Resource<List<Nft>>>{
        return repository.getNft(contract_address, chain)
    }
}