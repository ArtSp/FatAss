package com.asp.fatass.core.data.remote.products

import com.asp.fatass.NetworkConstants
import com.asp.fatass.core.data.remote.products.models.ProductsDTO
import com.asp.fatass.core.domain.products.ProductsClient
import com.asp.fatass.core.domain.products.models.CategoryItem
import com.asp.fatass.core.domain.products.models.ProductItem
import com.asp.fatass.core.domain.util.error.AppError
import com.asp.fatass.core.domain.util.error.AppException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorProductsClient(
    private val httpClient: HttpClient
) : ProductsClient {

    private suspend fun request(
        path: String
    ): HttpResponse {
        val response = try {
            httpClient.get {
                url(NetworkConstants.BASE_URL + path)
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw AppException(AppError.ServiceUnavailable)
        }

        when (response.status.value) {
            in 200..299 -> Unit
            500 -> throw AppException(AppError.ServerError("${response.status.value}"))
            in 400..499 -> throw AppException(AppError.ClientError("${response.status.value}"))
            else -> throw AppException(AppError.UnknownError)
        }

        return  response
    }

    override suspend fun getCategories(): List<CategoryItem> {
        val result = request("/products/categories")

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
        val result = request("/products/category/$category")

        return try {
            val body = result.body<ProductsDTO>()

            body.products.map {
                ProductItem(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    thumbnail = it.thumbnail,
                    price = it.price,
                    discountPercentage = it.discountPercentage
                )
            }
        } catch (e: Exception) {
            throw AppException(AppError.ServerError("${e.message}"))
        }
    }
}