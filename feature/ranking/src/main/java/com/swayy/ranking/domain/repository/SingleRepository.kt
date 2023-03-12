package com.swayy.ranking.domain.repository

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Single
import kotlinx.coroutines.flow.Flow

interface SingleRepository {
    fun getSingle(key: String, chain: String, exchange: String): Flow<Resource<Single>>
}