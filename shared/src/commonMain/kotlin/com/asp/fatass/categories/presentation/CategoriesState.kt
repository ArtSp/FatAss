package com.asp.fatass.categories.presentation

import com.asp.fatass._core.domain.util.error.AppError
import com.asp.fatass._core.domain.products.models.CategoryItem

data class CategoriesState (
    val isChoosingLanguage: Boolean = false,
    val error: AppError? = null,
    val categories: List<CategoryItem>? = null,
    val selectedCategory: CategoryItem? = null,
    val isLoading: Set<Content> = setOf()
) {
    enum class Content {
        CATEGORIES
    }
}