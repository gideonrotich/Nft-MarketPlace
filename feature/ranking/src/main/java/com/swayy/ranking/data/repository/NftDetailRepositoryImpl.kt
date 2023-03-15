package com.swayy.ranking.data.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.BlockspanApi
import com.swayy.ranking.data.mapper.toNftDetail
import com.swayy.ranking.data.mapper.toSingle
import com.swayy.ranking.domain.model.NftDetail
import com.swayy.ranking.domain.repository.NftDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NftDetailRepositoryImpl(
    private val blockspanApi: BlockspanApi
) :NftDetailRepository{
    override fun getNftDetail(
        contract_address: String,
        token_id: String,
        chain:String
    ): Flow<Resource<NftDetail>> = flow{
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
        val allMatches = blockspanApi.getNftDetail(contract_address, token_id,chain).toNftDetail()
        emit(Resource.Success(allMatches))
    }
}