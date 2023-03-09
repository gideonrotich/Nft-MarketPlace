/*
 * Copyright 2022 Joel Kanyi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.swayy.core.data

import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.swayy.core.model.MealPlanPreference
import com.swayy.core.util.Constants.THEME_OPTIONS
import com.swayy.core.util.Constants.WALLET_ADDRESS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealTimePreferences(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun saveTheme(themeValue: Int) {
        dataStore.edit { preferences ->
            preferences[THEME_OPTIONS] = themeValue
        }
    }

    val getTheme: Flow<Int> = dataStore.data.map { preferences ->
        preferences[THEME_OPTIONS] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    suspend fun saveMealPlanPreferences(
        walletAddress: String

    ) {
        dataStore.edit { preferences ->
            preferences[WALLET_ADDRESS] = walletAddress
        }
    }

    val mealPlanPreferences: Flow<MealPlanPreference?> = dataStore.data.map { preferences ->
        MealPlanPreference(
            walletAddress = preferences[WALLET_ADDRESS] ?: "0"
        )
    }

}
