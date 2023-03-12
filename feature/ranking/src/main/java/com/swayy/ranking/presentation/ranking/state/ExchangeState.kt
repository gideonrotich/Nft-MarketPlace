package com.swayy.ranking.presentation.ranking.state

import com.swayy.ranking.domain.model.Exchange
import com.swayy.ranking.domain.model.Ranking

data class ExchangeState(
    val isLoading: Boolean = false,
    val error: String = "",
    val exchange: List<Exchange> = emptyList()
)
