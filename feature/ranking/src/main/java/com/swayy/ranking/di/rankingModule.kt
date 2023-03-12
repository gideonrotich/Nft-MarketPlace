package com.swayy.ranking.di

import com.swayy.core_network.BlockspanApi
import com.swayy.core_network.MoralisApi
import com.swayy.ranking.data.repository.ExchangeRepositoryImpl
import com.swayy.ranking.data.repository.RankingRepositoryImpl
import com.swayy.ranking.data.repository.SingleRepositoryImpl
import com.swayy.ranking.domain.repository.ExchangeRepository
import com.swayy.ranking.domain.repository.RankingRepository
import com.swayy.ranking.domain.repository.SingleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object rankingModule {

    @Provides
    @Singleton
    fun provideExchangeRepository(blockspanApi: BlockspanApi): ExchangeRepository {
        return ExchangeRepositoryImpl(blockspanApi = blockspanApi)
    }

    @Provides
    @Singleton
    fun provideRankingRepository(blockspanApi: BlockspanApi): RankingRepository {
        return RankingRepositoryImpl(blockspanApi = blockspanApi)
    }

    @Provides
    @Singleton
    fun provideSingleRepository(blockspanApi: BlockspanApi): SingleRepository {
        return SingleRepositoryImpl(blockspanApi = blockspanApi)
    }

}