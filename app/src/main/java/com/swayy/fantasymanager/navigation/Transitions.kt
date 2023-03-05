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
package com.kanyideveloper.mealtime.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween

private const val AnimDurationLong = 500
private const val AnimDurationShort = 300

// Enter transition when you navigate to a screen
@ExperimentalAnimationApi
fun scaleInEnterTransition() = scaleIn(
    initialScale = .9f,
    animationSpec = tween(AnimDurationLong)
) + fadeIn(
    animationSpec = tween(AnimDurationShort)
)

// Exit transition when you navigate to a screen
@ExperimentalAnimationApi
fun scaleOutExitTransition() = scaleOut(
    targetScale = 1.1f,
    animationSpec = tween(AnimDurationShort)
) + fadeOut(
    animationSpec = tween(AnimDurationShort)
)

// Enter transition of a screen when you pop to it
@ExperimentalAnimationApi
fun scaleInPopEnterTransition() = scaleIn(
    initialScale = 1.1f,
    animationSpec = tween(AnimDurationLong)
) + fadeIn(
    animationSpec = tween(AnimDurationShort)
)

// Exit transition of a screen you are popping from
@ExperimentalAnimationApi
fun scaleOutPopExitTransition() = scaleOut(
    targetScale = .9f,
    animationSpec = tween(AnimDurationShort)
) + fadeOut(
    animationSpec = tween(AnimDurationShort)
)
