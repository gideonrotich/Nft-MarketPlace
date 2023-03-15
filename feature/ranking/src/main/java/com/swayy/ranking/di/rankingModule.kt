package com.swayy.ranking.di

import com.swayy.core_network.BlockspanApi
import com.swayy.core_network.MoralisApi
import com.swayy.ranking.data.repository.*
import com.swayy.ranking.domain.repository.*
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

    @Provides
    @Singleton
    fun provideNftRepository(blockspanApi: BlockspanApi): NftRepository {
        return NftRepositoryImpl(blockspanApi = blockspanApi)
    }

    @Provides
    @Singleton
    fun provideNftDetailRepository(blockspanApi: BlockspanApi): NftDetailRepository {
        return NftDetailRepositoryImpl(blockspanApi = blockspanApi)
    }

}