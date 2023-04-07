package com.swayy.fantasymanager

import androidx.annotation.StringRes

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
}