package com.swayy.core.util

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    val THEME_OPTIONS = intPreferencesKey(name = "theme_option")
    const val MEALTIME_PREFERENCES = "MEALTIME_PREFERENCES"
    val WALLET_ADDRESS = stringPreferencesKey("wallet_address")
}