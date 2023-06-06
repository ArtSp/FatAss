package com.asp.fatass.categories.presentation

import com.asp.fatass.core.presentation.UiLanguage
import com.asp.fatass.core.domain.products.models.CategoryItem

sealed class CategoriesEvent {
    data class OpenLanguageDropDown(val value: Boolean): CategoriesEvent()
    data class ChooseLanguage(val language: UiLanguage): CategoriesEvent()
    data class ChooseCategory(val category: CategoryItem?): CategoriesEvent()
    object LoadContent: CategoriesEvent()
    object ClearError: CategoriesEvent()
}