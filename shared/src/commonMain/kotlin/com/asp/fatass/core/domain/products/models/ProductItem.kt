package com.asp.fatass.core.domain.products.models

import com.asp.fatass.core.domain.util.Identifier

data class ProductItem(
    val id: Identifier,
    val title: String
)
