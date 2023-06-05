package com.asp.fatass.core.data.remote.products

import com.asp.fatass.NetworkConstants
import com.asp.fatass.core.domain.products.ProductsClient
import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.products.models.ProductItem
import com.asp.fatass.core.domain.util.error.AppError
import com.asp.fatass.core.domain.util.error.AppException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorProductsClient(
    private val httpClient: HttpClient
) : ProductsClient {

    override suspend fun getCategories(): List<CategoryItem> {
        val result = try {
            httpClient.get {
                url(NetworkConstants.BASE_URL + "/products/categories")
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw AppException(AppError.ServiceUnavailable)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw AppException(AppError.ServerError("${result.status.value}"))
            in 400..499 -> throw AppException(AppError.ClientError("${result.status.value}"))
            else -> throw AppException(AppError.UnknownError)
        }

        return try {
            val categories = result.body<List<String>>()

            categories.mapIndexed { i, title ->
                CategoryItem(
                    id = i.toLong(),
                    name = title
                )
            }
        } catch (e: Exception) {
            throw AppException(AppError.ServerError("${e.message}"))
        }
    }

    override suspend fun getProducts(
        category: CategoryItem
    ): List<ProductItem> {
        TODO("Not implemented")
    }
}