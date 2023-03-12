package com.swayy.ranking.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Single
import com.swayy.ranking.domain.repository.SingleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getSingleUseCase @Inject constructor(
    private val repository: SingleRepository
) {
    operator fun invoke(key: String, chain: String, exchange: String): Flow<Resource<Single>> {
        return repository.getSingle(
            key, chain, exchange
        )
    }
}