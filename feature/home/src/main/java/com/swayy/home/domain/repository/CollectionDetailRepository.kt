package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import com.swayy.core_network.model.collection_details.Data
import com.swayy.home.domain.model.CollectionDetail
import kotlinx.coroutines.flow.Flow


interface CollectionDetailRepository {
    fun getCollectionDetail(collection:String):Flow<Resource<CollectionDetail>>
}