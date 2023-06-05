package com.asp.fatass.core.domain.products

import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.util.CommonFlow

interface ProductsDataSource {
    fun getCategories(): CommonFlow<List<CategoryItem>>
    suspend fun setCategories(items: List<CategoryItem>)
}