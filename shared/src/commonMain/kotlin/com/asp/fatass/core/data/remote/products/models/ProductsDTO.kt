package com.asp.fatass.core.data.remote.products.models

import com.asp.fatass.core.domain.util.Identifier
import com.asp.fatass.core.domain.util.Price

@kotlinx.serialization.Serializable
data class ProductsDTO(
    val products: List<ProductItemDTO>
) {
    @kotlinx.serialization.Serializable
    data class ProductItemDTO(
        val id: Identifier,
        val title: String,
        val description: String,
        val price: Price,
        val discountPercentage: Float?,
        val rating: Float,
        val stock: Int,
        val brand: String,
        val category: String,
        val thumbnail: String,
        val images: List<String>
    )
}