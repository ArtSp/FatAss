package com.asp.fatass.categories.presentation

import com.asp.fatass.core.presentation.UiLanguage
import com.asp.fatass.core.domain.products.models.CategoryItem

sealed class CategoriesEvent {
    object OpenLanguageDropDown: CategoriesEvent()
    object CloseLanguageDropDown: CategoriesEvent()
    data class ChooseLanguage(val language: UiLanguage): CategoriesEvent()
    data class ChooseSection(val section: CategoryItem): CategoriesEvent()

    object LoadContent: CategoriesEvent()
}