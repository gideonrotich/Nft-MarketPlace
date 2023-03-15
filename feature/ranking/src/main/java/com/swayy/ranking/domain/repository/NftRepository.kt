package com.swayy.ranking.domain.repository

import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.Nft
import kotlinx.coroutines.flow.Flow

interface NftRepository {
    fun getNft(contract_address:String,chain:String):Flow<Resource<List<Nft>>>
}