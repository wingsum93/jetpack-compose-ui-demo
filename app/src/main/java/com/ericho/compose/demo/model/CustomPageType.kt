package com.ericho.compose.demo.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

typealias CustomPageInvoke = @Composable (NavBackStackEntry) -> Unit