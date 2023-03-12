package com.swayy.ranking.presentation.ranking

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swayy.core.util.Resource
import com.swayy.ranking.domain.use_case.getExchangeUseCase
import com.swayy.ranking.domain.use_case.getRankingUseCase
import com.swayy.ranking.domain.use_case.getSingleUseCase
import com.swayy.ranking.presentation.ranking.state.ExchangeState
import com.swayy.ranking.presentation.ranking.state.RankingListState
import com.swayy.ranking.presentation.ranking.state.SingleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val getRankingUseCase: getRankingUseCase,
    private val getSingleUseCase: getSingleUseCase,
    private val getExchangeUseCase: getExchangeUseCase
) : ViewModel() {

    private val _ranking = mutableStateOf(RankingListState())
    val ranking: State<RankingListState> = _ranking

    private val _single = mutableStateOf(SingleState())
    val single: State<SingleState> = _single

    private val _exchange = mutableStateOf(ExchangeState())
    val exchange: State<ExchangeState> = _exchange

    init {
        getRankings("eth-main","opensea","total_volume")
    }

    fun getRankings(chain: String, exchange: String, ranking: String) {
        getRankingUseCase(chain, exchange, ranking).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _ranking.value = RankingListState(ranking = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _ranking.value = RankingListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _ranking.value = RankingListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getExchange("eth-main","opensea")
    }

    fun getExchange(chain: String, exchange: String) {
        getExchangeUseCase(chain, exchange).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _exchange.value = ExchangeState(exchange = result.data ?: emptyList())

                }
                is Resource.Error -> {
                    _exchange.value = ExchangeState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _exchange.value = ExchangeState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getSingle(key: String, chain: String, exchange: String) {
        getSingleUseCase(key, chain, exchange).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _single.value = SingleState(single = result.data)

                }
                is Resource.Error -> {
                        _single.value = SingleState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _single.value = SingleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}