package com.swayy.home.presentation.collection_detail.state

import com.swayy.home.domain.model.CollectionDetail

data class CollectionDetailState(
    val isLoading: Boolean = false,
    val error: String = "",
    val collectiondetail: CollectionDetail? = null,
)
