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
package com.swayy.profile.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.core.state.TextFieldState
import com.swayy.core.domain.UserDataRepository
import com.swayy.core.util.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    private val _eventsFlow = MutableSharedFlow<UiEvents>()
    val eventsFlow = _eventsFlow

    private val _shouldShowThemesDialog = mutableStateOf(false)
    val shouldShowThemesDialog: State<Boolean> = _shouldShowThemesDialog
    fun setShowThemesDialogState(value: Boolean) {
        _shouldShowThemesDialog.value = value
    }

    private val _shouldShowFeedbackDialog = mutableStateOf(false)
    val shouldShowFeedbackDialog: State<Boolean> = _shouldShowFeedbackDialog
    fun setShowFeedbackDialogState(value: Boolean) {
        _shouldShowFeedbackDialog.value = value
    }

    private val _feedback = mutableStateOf(TextFieldState())
    val feedback: State<TextFieldState> = _feedback
    fun setFeedbackState(value: String) {
        _feedback.value = feedback.value.copy(
            text = value,
            error = null
        )
    }

    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            userDataRepository.setTheme(themeValue = themeValue)
            setShowThemesDialogState(false)
        }
    }

    fun validateFeedbackTextfield(message: String) {
        if (message.isEmpty()) {
            _feedback.value = feedback.value.copy(
                error = "Feedback cannot be empty"
            )
        }
    }
}
