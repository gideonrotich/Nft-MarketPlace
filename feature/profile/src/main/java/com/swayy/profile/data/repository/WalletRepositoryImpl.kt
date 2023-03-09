package com.swayy.profile.data.repository

import android.content.Context
import com.swayy.core.data.MealTimePreferences
import com.swayy.core.model.MealPlanPreference
import com.swayy.profile.domain.repository.WalletRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletRepositoryImpl(
    private val mealTimePreferences: MealTimePreferences,
) : WalletRepository {

    override suspend fun saveMealPlannerPreferences(walletAddress: String) {
        mealTimePreferences.saveMealPlanPreferences(
            walletAddress = walletAddress
        )
    }

    override val hasMealPlanPref: Flow<MealPlanPreference?>
        get() = mealTimePreferences.mealPlanPreferences
}
