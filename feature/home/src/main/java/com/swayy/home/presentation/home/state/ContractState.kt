package com.swayy.home.presentation.home.state

import com.swayy.home.domain.model.Contract

data class ContractState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val contracts: List<Contract> = emptyList()
)
