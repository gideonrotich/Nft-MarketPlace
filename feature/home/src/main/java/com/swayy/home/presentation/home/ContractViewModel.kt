package com.swayy.home.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.repository.ContractRepository
import com.swayy.home.domain.use_case.GetContractUseCase
import com.swayy.home.presentation.home.state.ContractState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContractViewModel @Inject constructor(
    private val getContractUseCase: GetContractUseCase
) : ViewModel() {

    private val _contracts = mutableStateOf(ContractState())
    val contracts: State<ContractState> = _contracts

    init {
        getContracts("0x3bf2922f4520a8BA0c2eFC3D2a1539678DaD5e9D", 8,true)
    }

    fun getContracts(address:String,limit:Int,normalizedMetadata: Boolean) {
        getContractUseCase(address,limit,normalizedMetadata).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _contracts.value = ContractState(contracts = result.data ?: emptyList())
//
                }
                is Resource.Error -> {
                    _contracts.value = ContractState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _contracts.value = ContractState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}