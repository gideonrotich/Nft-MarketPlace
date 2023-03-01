package com.swayy.home.domain.use_case

import com.swayy.core.util.Resource
import com.swayy.home.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    operator fun invoke():Flow<Resource<List<com.swayy.home.domain.model.Collection>>>{
        return repository.getCollections()
    }
}