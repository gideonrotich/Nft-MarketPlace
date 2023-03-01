package com.swayy.home.presentation.home.state

import com.swayy.home.domain.model.Contract

data class CollectionState(
    val isLoading: Boolean = false,
    val error: String = "",
    val collections: List<com.swayy.home.domain.model.Collection> = emptyList()
)
