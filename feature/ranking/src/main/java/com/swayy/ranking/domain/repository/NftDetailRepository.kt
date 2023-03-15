package com.swayy.ranking.domain.repository

import androidx.lifecycle.LiveData
import com.swayy.core.util.Resource
import com.swayy.ranking.domain.model.NftDetail
import kotlinx.coroutines.flow.Flow

interface NftDetailRepository {
    fun getNftDetail(contract_address:String,token_id:String,chain:String):Flow<Resource<NftDetail>>
}