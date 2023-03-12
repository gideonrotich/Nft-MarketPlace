package com.swayy.ranking.presentation.ranking.state

import com.swayy.ranking.domain.model.Ranking
import com.swayy.ranking.domain.model.Single

data class RankingListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val ranking: List<Ranking> = emptyList()
)
