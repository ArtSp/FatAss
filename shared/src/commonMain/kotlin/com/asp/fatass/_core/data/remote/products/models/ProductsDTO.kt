package com.asp.fatass._core.data.remote.products.models

import com.asp.fatass._core.domain.util.Identifier
import com.asp.fatass._core.domain.util.Price
import kotlinx.serialization.Serializable

@Serializable
data class ProductsDTO(
    val products: List<ProductItemDTO>,
    val total: Int,
    val skip: Int,
    val limit: Int
) {
    @Serializable
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