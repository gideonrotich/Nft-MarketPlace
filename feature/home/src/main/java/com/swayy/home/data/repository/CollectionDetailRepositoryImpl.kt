package com.swayy.home.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.HowrareApi
import com.swayy.core_network.model.collection_details.Data
import com.swayy.home.data.toCollection
import com.swayy.home.data.toCollectionDetail
import com.swayy.home.domain.model.CollectionDetail
import com.swayy.home.domain.repository.CollectionDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CollectionDetailRepositoryImpl(
    private val howrareApi: HowrareApi
) : CollectionDetailRepository {
    override fun getCollectionDetail(collection: String): Flow<Resource<CollectionDetail>> = flow {
        emit(Resource.Loading())
        try {
            val apiResponse = howrareApi.getCollectionDetails(collection)

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
        val allMatches = howrareApi.getCollectionDetails(collection).result.data.toCollectionDetail()
        emit(Resource.Success(allMatches))
    }
}