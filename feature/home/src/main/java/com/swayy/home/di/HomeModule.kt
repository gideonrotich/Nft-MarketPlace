package com.swayy.home.di

import com.swayy.core_network.HowrareApi
import com.swayy.core_network.MoralisApi
import com.swayy.home.data.repository.CollectionDetailRepositoryImpl
import com.swayy.home.data.repository.CollectionRepositoryImpl
import com.swayy.home.data.repository.ContractRepositoryImpl
import com.swayy.home.domain.repository.CollectionDetailRepository
import com.swayy.home.domain.repository.CollectionRepository
import com.swayy.home.domain.repository.ContractRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideContractRepository(moralisApi: MoralisApi): ContractRepository {
        return ContractRepositoryImpl(moralisApi = moralisApi)
    }

    @Provides
    @Singleton
    fun provideCollectionRepository(howrareApi: HowrareApi): CollectionRepository {
        return CollectionRepositoryImpl(howrareApi = howrareApi)
    }

    @Provides
    @Singleton
    fun provideCollectionDetailRepository(howrareApi: HowrareApi): CollectionDetailRepository {
        return CollectionDetailRepositoryImpl(howrareApi = howrareApi)
    }

}