package com.swayy.home.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.home.domain.model.CollectionDetail
import com.swayy.home.domain.repository.CollectionDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionDetailUseCase @Inject constructor(
    private val repository: CollectionDetailRepository
) {
    operator fun invoke(collection:String):Flow<Resource<CollectionDetail>>{
        return repository.getCollectionDetail(collection)
    }
}