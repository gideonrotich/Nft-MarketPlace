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
package com.swayy.fantasymanager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.kanyideveloper.mealtime.navigation.NavGraphs
import com.ramcosta.composedestinations.spec.NavGraphSpec

sealed class BottomNavItem(var title: String, var icon: Int, var screen: NavGraphSpec) {
    object Home : BottomNavItem(
        title = "Home",
        icon = com.swayy.core.R.drawable.ic_home,
        screen = NavGraphs.home
    )

    object Search : BottomNavItem(
        title = "Ranking",
        icon = com.swayy.core.R.drawable.baseline_equalizer_24,
        screen = NavGraphs.home
    )

    object MealPlanner : BottomNavItem(
        title = "Search",
        icon = com.swayy.core.R.drawable.baseline_search_24,
        screen = NavGraphs.home
    )

    object Favorites : BottomNavItem(
        title = "Profile",
        icon =com.swayy.core.R.drawable.baseline_person_outline_24,
        screen = NavGraphs.home
    )

    object Settings : BottomNavItem(
        title = "More",
        icon = com.swayy.core.R.drawable.baseline_dehaze_24,
        screen = NavGraphs.home
    )
}
