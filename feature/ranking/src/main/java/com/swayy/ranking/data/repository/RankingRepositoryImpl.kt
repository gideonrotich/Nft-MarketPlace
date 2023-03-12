package com.swayy.ranking.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.BlockspanApi
import com.swayy.ranking.data.mapper.toExchange
import com.swayy.ranking.data.mapper.toRanking
import com.swayy.ranking.domain.model.Ranking
import com.swayy.ranking.domain.repository.RankingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RankingRepositoryImpl(
    private val blockspanApi: BlockspanApi
) : RankingRepository {
    override fun getRanking(
        chain: String,
        exchange: String,
        ranking: String
    ): Flow<Resource<List<Ranking>>> = flow {
        emit(Resource.Loading())
        try {
            val apiResponse = blockspanApi.getRanking(chain, exchange, ranking)

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
            blockspanApi.getRanking(chain, exchange, ranking).results.map { it.toRanking() }
        emit(Resource.Success(allMatches))
    }
}