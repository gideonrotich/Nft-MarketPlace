package com.swayy.ranking.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.BlockspanApi
import com.swayy.ranking.data.mapper.toExchange
import com.swayy.ranking.domain.model.Exchange
import com.swayy.ranking.domain.repository.ExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ExchangeRepositoryImpl(
    private val blockspanApi: BlockspanApi
) : ExchangeRepository {
    override fun getExchange(chain: String, exchange: String): Flow<Resource<List<Exchange>>> =
        flow {
            emit(Resource.Loading())
            try {
                val apiResponse = blockspanApi.getExchange(chain, exchange)

            } catch (exception: IOException) {
                emit(
                    Resource.Error(
                        message = "Connection Lost"
                    )
                )
            } catch (exception: HttpException) {
                emit(
                    Resource.Error(
                        message = exception.message()
                    )
                )
            }
            val allMatches =
                blockspanApi.getExchange(chain, exchange).results.map { it.toExchange() }
            emit(Resource.Success(allMatches))
        }

}