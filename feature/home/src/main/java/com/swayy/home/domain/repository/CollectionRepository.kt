package com.swayy.home.domain.repository

import com.swayy.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getCollections():Flow<Resource<List<com.swayy.home.domain.model.Collection>>>
}