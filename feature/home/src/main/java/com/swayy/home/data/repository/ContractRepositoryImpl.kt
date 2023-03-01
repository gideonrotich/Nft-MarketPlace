package com.swayy.home.data.repository

import com.swayy.core.util.Resource
import com.swayy.core.util.safeApiCall
import com.swayy.core_network.MoralisApi
import com.swayy.core_network.model.nft_contract.NormalizedMetadata
import com.swayy.home.data.toData
import com.swayy.home.domain.model.Contract
import com.swayy.home.domain.repository.ContractRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ContractRepositoryImpl(
    private val moralisApi: MoralisApi
) : ContractRepository {
    override fun getContracts(
        address: String,
        limit: Int,
        normalizedMetadata: Boolean
    ): Flow<Resource<List<Contract>>> = flow {
        emit(Resource.Loading())
        try {
            val apiResponse = moralisApi.getContract(address,limit,normalizedMetadata)
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
        val allMatches = moralisApi.getContract(address,limit,normalizedMetadata).result.map { it.toData() }
        emit(Resource.Success(allMatches))
    }

}