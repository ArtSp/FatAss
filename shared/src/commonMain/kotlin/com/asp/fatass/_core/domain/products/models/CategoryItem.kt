package com.asp.fatass._core.domain.products.models

import com.asp.fatass._core.domain.util.Identifier

data class CategoryItem(
    val id: Identifier,
    val name: String
) {
    val displayName: String
        get() = name.replace("-",  " ").replaceFirstChar { it.uppercase() }
}