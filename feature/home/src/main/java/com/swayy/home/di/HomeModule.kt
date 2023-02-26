package com.swayy.home.di

import com.swayy.core_network.MoralisApi
import com.swayy.home.data.repository.ContractRepositoryImpl
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
}