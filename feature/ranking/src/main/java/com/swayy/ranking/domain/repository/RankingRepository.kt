package com.swayy.ranking.domain.repository

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Exchange
import com.swayy.ranking.domain.model.Ranking
import kotlinx.coroutines.flow.Flow

interface RankingRepository {
    fun getRanking(chain: String, exchange: String, ranking: String): Flow<Resource<List<Ranking>>>
}