package com.asp.fatass._core.domain.products.models

import com.asp.fatass._core.domain.util.Identifier
import com.asp.fatass._core.domain.util.Price

data class ProductItem(
    val id: Identifier,
    val title: String,
    val description: String,
    val thumbnail: String,
    val price: Price,
    val discountPercentage: Float?
)
