package com.asp.fatass.core.domain.products

import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.util.Resource
import com.asp.fatass.core.domain.util.error.AppException

class ProductsUseCase(
    private val client: ProductsClient,
    private val dataSource: ProductsDataSource
) {

    suspend fun getCategories(): Resource<List<CategoryItem>> {
        return try {
            val categories = client.getCategories().also {
                //dataSource.setCategories(it)
            }
            Resource.Success(categories)
        } catch(e: AppException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

    suspend fun getProducts(
        categoryItem: CategoryItem
    ): Resource<List<CategoryItem>> {
        TODO("Not yet implemented")
    }

}