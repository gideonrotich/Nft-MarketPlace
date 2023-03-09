package com.swayy.profile.domain.repository

import com.swayy.core.model.MealPlanPreference
import kotlinx.coroutines.flow.Flow

interface WalletRepository {
    val hasMealPlanPref: Flow<MealPlanPreference?>

    suspend fun saveMealPlannerPreferences(
        walletAddress: String
    )
}