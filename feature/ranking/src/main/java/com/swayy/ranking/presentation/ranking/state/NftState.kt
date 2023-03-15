package com.swayy.ranking.presentation.ranking.state

import com.swayy.ranking.domain.model.Nft
import com.swayy.ranking.domain.model.Ranking

data class NftState(
    val isLoading: Boolean = false,
    val error: String = "",
    val nft: List<Nft> = emptyList()
)
