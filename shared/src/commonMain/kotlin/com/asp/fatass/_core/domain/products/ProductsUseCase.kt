package com.asp.fatass._core.domain.products

import com.asp.fatass._core.domain.products.models.CategoryItem
import com.asp.fatass._core.domain.products.models.ProductItem
import com.asp.fatass._core.domain.util.Resource
import com.asp.fatass._core.domain.util.error.AppException
import com.asp.fatass._core.domain.util.flow.CommonFlow

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

    fun getCategoriesCache(): CommonFlow<List<CategoryItem>> {
        return dataSource.getCategories()
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