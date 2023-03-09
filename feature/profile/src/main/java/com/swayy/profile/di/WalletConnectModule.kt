package com.swayy.profile.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.swayy.core.data.MealTimePreferences
import com.swayy.profile.data.repository.WalletRepositoryImpl
import com.swayy.profile.domain.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.walletconnect.impls.FileWCSessionStore
import org.walletconnect.impls.WCSessionStore
import java.io.File
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WalletConnectModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()


    @Provides
    @Singleton
    fun provideSessionStorage(
        @ApplicationContext context: Context,
        moshi: Moshi,
    ): WCSessionStore = FileWCSessionStore(File(context.cacheDir, "session_store.json").apply { createNewFile() },
        moshi)

    @Provides
    @Singleton
    fun providesWalletRepository(
        mealTimePreferences: MealTimePreferences
    ): WalletRepository {
        return WalletRepositoryImpl(
            mealTimePreferences = mealTimePreferences
        )
    }
}