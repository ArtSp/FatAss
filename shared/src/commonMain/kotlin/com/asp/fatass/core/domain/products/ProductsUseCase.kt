package com.asp.fatass.core.domain.products

import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.products.models.ProductItem
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
        category: CategoryItem
    ): Resource<List<ProductItem>> {
        return try {
            val products = client.getProducts(category)
            Resource.Success(products)
        } catch(e: AppException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

}