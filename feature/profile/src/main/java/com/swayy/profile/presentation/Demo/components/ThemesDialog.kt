/*
 * Copyright 2023 Joel Kanyi.
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
package com.kanyideveloper.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.swayy.compose_ui.theme.Theme
import com.swayy.profile.R
import com.swayy.profile.presentation.Demo.components.ThemeItem

@Composable
fun ThemesDialog(
    onDismiss: () -> Unit,
    onSelectTheme: (Int) -> Unit
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Themes",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                ThemeItem(
                    themeName = "Use System Settings",
                    themeValue = Theme.FOLLOW_SYSTEM.themeValue,
                    icon = com.swayy.core.R.drawable.settings_suggest,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Light Mode",
                    themeValue = Theme.LIGHT_THEME.themeValue,
                    icon = com.swayy.core.R.drawable.light_mode,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Dark Mode",
                    themeValue = Theme.DARK_THEME.themeValue,
                    icon = com.swayy.core.R.drawable.dark_mode,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Material You",
                    themeValue = Theme.MATERIAL_YOU.themeValue,
                    icon = com.swayy.core.R.drawable.wallpaper,
                    onSelectTheme = onSelectTheme
                )
            }
        },
        confirmButton = {
            Text(
                text = "OK",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    )
}
