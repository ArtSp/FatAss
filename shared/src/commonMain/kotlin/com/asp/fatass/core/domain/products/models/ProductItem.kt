package com.asp.fatass.core.domain.products.models

import com.asp.fatass.core.domain.util.Identifier
import com.asp.fatass.core.domain.util.Price

data class ProductItem(
    val id: Identifier,
    val title: String,
    val description: String,
    val thumbnail: String,
    val price: Price,
    val discountPercentage: Float?
)
