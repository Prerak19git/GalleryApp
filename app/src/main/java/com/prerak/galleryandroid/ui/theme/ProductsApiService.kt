package com.prerak.galleryandroid.ui.theme

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import retrofit2.http.GET

private const val BASE_URL = "https://www.inito.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ProductsApiService{
    @GET("products/list")
    suspend fun getProducts() : OverallProduct
}

object ProductsApi {
    val retrofitService : ProductsApiService by lazy {
        retrofit.create(ProductsApiService::class.java)
    }
}