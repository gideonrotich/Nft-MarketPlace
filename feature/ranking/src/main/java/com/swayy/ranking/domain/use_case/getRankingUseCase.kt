package com.swayy.ranking.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Ranking
import com.swayy.ranking.domain.repository.RankingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getRankingUseCase @Inject constructor(
    private val repository: RankingRepository
) {
    operator fun invoke(chain:String,exchange:String,ranking:String):Flow<Resource<List<Ranking>>>{
        return repository.getRanking(chain, exchange, ranking)
    }
}