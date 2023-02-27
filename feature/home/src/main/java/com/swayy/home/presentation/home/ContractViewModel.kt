package com.swayy.home.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.repository.ContractRepository
import com.swayy.home.presentation.home.state.ContractState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContractViewModel @Inject constructor(
    private val contractRepository: ContractRepository
) : ViewModel() {

    private val _contracts = mutableStateOf(ContractState())
    val contracts: State<ContractState> = _contracts

    init {
        getContracts("0x3bf2922f4520a8BA0c2eFC3D2a1539678DaD5e9D", true)
    }


    fun getContracts(address: String, normalizedMetadata: Boolean) {
        _contracts.value = contracts.value.copy(
            isLoading = true,
            contracts = emptyList(),
            error = null
        )
        viewModelScope.launch {
            when (val result = contractRepository.getContracts(
                address = address,
                normalizedMetadata = normalizedMetadata
            )) {
                is Resource.Error -> {
                    _contracts.value = contracts.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Success -> {
                    _contracts.value = contracts.value.copy(
                        isLoading = false,
                        contracts = result.data ?: emptyList()
                    )
                }
                else -> {
                    contracts
                }
            }
        }
    }
}