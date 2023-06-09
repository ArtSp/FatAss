package com.asp.fatass.android.categories.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.asp.fatass.categories.presentation.CategoriesEvent
import com.asp.fatass.categories.presentation.CategoriesState

@Composable
fun CategoriesScreen(
    state: CategoriesState,
    onEvent: (CategoriesEvent) -> Unit
) {
    val context = LocalContext.current

}