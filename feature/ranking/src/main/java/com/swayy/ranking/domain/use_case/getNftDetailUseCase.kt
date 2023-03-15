package com.swayy.ranking.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.NftDetail
import com.swayy.ranking.domain.repository.NftDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getNftDetailUseCase @Inject constructor(
    private val repository: NftDetailRepository
) {
    operator fun invoke(contract_address:String,token_id:String,chain:String):Flow<Resource<NftDetail>>{
        return repository.getNftDetail(contract_address, token_id,chain)
    }
}