package com.swayy.ranking.presentation.ranking.state

import com.swayy.ranking.domain.model.NftDetail
import com.swayy.ranking.domain.model.Single

data class NftDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val nftDetail: NftDetail? = null
)
