package com.swayy.ranking.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.BlockspanApi
import com.swayy.ranking.data.mapper.toSingle
import com.swayy.ranking.domain.model.Single
import com.swayy.ranking.domain.repository.SingleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SingleRepositoryImpl(
    private val blockspanApi: BlockspanApi
) : SingleRepository {
    override fun getSingle(key: String, chain: String, exchange: String): Flow<Resource<Single>> =
        flow {
            emit(Resource.Loading())
            try {

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
            val allMatches = blockspanApi.getSingle(key, chain, exchange).toSingle()
            emit(Resource.Success(allMatches))
        }
}