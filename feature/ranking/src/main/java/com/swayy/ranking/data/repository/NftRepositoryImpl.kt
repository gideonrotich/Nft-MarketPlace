package com.swayy.ranking.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.BlockspanApi
import com.swayy.ranking.data.mapper.toNft
import com.swayy.ranking.data.mapper.toRanking
import com.swayy.ranking.domain.model.Nft
import com.swayy.ranking.domain.repository.NftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NftRepositoryImpl(
    private val blockspanApi: BlockspanApi
) :NftRepository{
    override fun getNft(contract_address: String, chain: String): Flow<Resource<List<Nft>>> = flow{
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
        val allMatches =
            blockspanApi.getNft(contract_address,chain).results.map { it.toNft() }
        emit(Resource.Success(allMatches))
    }
}