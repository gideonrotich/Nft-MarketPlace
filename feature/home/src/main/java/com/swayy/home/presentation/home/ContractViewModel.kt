package com.swayy.home.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.repository.ContractRepository
import com.swayy.home.domain.use_case.GetCollectionUseCase
import com.swayy.home.domain.use_case.GetContractUseCase
import com.swayy.home.presentation.home.state.CollectionState
import com.swayy.home.presentation.home.state.ContractState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContractViewModel @Inject constructor(
    private val getContractUseCase: GetContractUseCase,
    private val getCollectionUseCase: GetCollectionUseCase
) : ViewModel() {

    private val _contracts = mutableStateOf(ContractState())
    val contracts: State<ContractState> = _contracts

    private val _collections = mutableStateOf(CollectionState())
    val collections: State<CollectionState> = _collections

    init {
        getCollections()
        getContracts("0x59468516a8259058baD1cA5F8f4BFF190d30E066", 8,true)
    }

    fun getCollections() {
        getCollectionUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _collections.value = CollectionState(collections = result.data ?: emptyList())
//
                }
                is Resource.Error -> {
                    _collections.value = CollectionState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _collections.value = CollectionState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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