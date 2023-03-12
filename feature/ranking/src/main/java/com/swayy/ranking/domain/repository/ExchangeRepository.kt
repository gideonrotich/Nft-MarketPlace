package com.swayy.ranking.domain.repository

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Exchange
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    fun getExchange(chain:String,exchange:String):Flow<Resource<List<Exchange>>>
}