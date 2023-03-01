package com.swayy.home.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.HowrareApi
import com.swayy.home.data.toCollection
import com.swayy.home.data.toData
import com.swayy.home.domain.model.Collection
import com.swayy.home.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CollectionRepositoryImpl(
    private val howrareApi: HowrareApi
) :CollectionRepository{
    override fun getCollections(): Flow<Resource<List<Collection>>> = flow {
        emit(Resource.Loading())
        try {
            val apiResponse = howrareApi.getCollections()
//            matchesDao.deleteMatches()
//            matchesDao.insertMatches(apiResponse.data.map { it.toEntity() })
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
        val allMatches = howrareApi.getCollections().result.data.map { it.toCollection() }
        emit(Resource.Success(allMatches))
    }
}