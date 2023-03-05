package com.swayy.home.presentation.collection_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.home.domain.use_case.CollectionDetailUseCase
import com.swayy.home.presentation.collection_detail.state.CollectionDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CollectionDetailViewmodel @Inject constructor(
    private val getCollectionDetailUseCase:CollectionDetailUseCase
) :ViewModel(){

    private val _state = mutableStateOf(CollectionDetailState())
    val state: State<CollectionDetailState> = _state


    fun getCollectionDetails(collection: String) {
        getCollectionDetailUseCase(collection).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CollectionDetailState(collectiondetail = result.data)

                }
                is Resource.Error -> {
                    _state.value = CollectionDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CollectionDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}