package com.swayy.ranking.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Exchange
import com.swayy.ranking.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getExchangeUseCase @Inject constructor(
    private val repository: ExchangeRepository
) {
    operator fun invoke(chain: String, exchange: String): Flow<Resource<List<Exchange>>> {
        return repository.getExchange(chain, exchange)
    }
}