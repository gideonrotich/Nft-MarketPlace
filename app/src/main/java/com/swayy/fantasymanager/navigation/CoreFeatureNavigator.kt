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
package com.swayy.fantasymanager.navigation

import android.net.Uri
import androidx.navigation.NavController
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.swayy.home.presentation.destinations.CollectionDetailScreenDestination
import com.swayy.home.presentation.destinations.HomeScreenDestination
import com.swayy.home.presentation.home.HomeNavigator
import com.swayy.profile.presentation.SettingsNavigator
import com.swayy.profile.presentation.destinations.SettingsScreenDestination
import com.swayy.ranking.presentation.ranking.RankingNavigator
import com.swayy.ranking.presentation.ranking.destinations.RankingScreenDestination
import com.swayy.search.presentation.SearchNavigator
import com.swayy.search.presentation.destinations.SearchScreenDestination
import com.swayy.settings.presentation.ProfileNavigator
import com.swayy.settings.presentation.destinations.ProfileScreenDestination

class CoreFeatureNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : HomeNavigator, SettingsNavigator,RankingNavigator,SearchNavigator ,ProfileNavigator{

    override fun openHome() {
        navController.navigate(HomeScreenDestination within navGraph)
    }

    override fun openRanking() {
        navController.navigate(RankingScreenDestination within navGraph)
    }

    override fun openSearch() {
        navController.navigate(SearchScreenDestination within navGraph)
    }

    override fun openSettings() {
        navController.navigate(ProfileScreenDestination within navGraph)
    }


    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun openCollectionDetails(collectionId: String) {
        navController.navigate(CollectionDetailScreenDestination(collectionId = collectionId) within navGraph)
    }

    override fun openAllergiesScreen() {}

}
