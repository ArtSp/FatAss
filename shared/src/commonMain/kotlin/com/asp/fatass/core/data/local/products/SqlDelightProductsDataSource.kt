package com.asp.fatass.core.data.local.products

import com.asp.fatass.core.domain.products.ProductsDataSource
import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.util.CommonFlow
import com.asp.fatass.database.ProductsDatabase

class SqlDelightProductsDataSource(
    db: ProductsDatabase
): ProductsDataSource {

    override fun getCategories(): CommonFlow<List<CategoryItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun setCategories(
        items: List<CategoryItem>
    ) {
        TODO("Not yet implemented")
    }

}