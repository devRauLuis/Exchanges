package com.devaruluis.exchanges.data.network

import com.devaruluis.exchanges.model.Coin
import retrofit2.Response
import retrofit2.http.*

interface CoinApiClient {
    @GET("/api/coins")
    suspend fun getAllCoins(): Response<List<Coin>>

    @GET("/api/coins/{id}")
    suspend fun getCoin(@Path("id") id: String): Response<Coin>

    @POST("/api/coins")
    suspend fun createCoin(@Body coin: Coin): Response<Coin>

    @DELETE("/api/coins/{id}")
    suspend fun deleteCoin(@Path("id") id: String): Response<Coin>

    @PUT("/api/coins/{id}")
    suspend fun updateCoin(@Path("id") id: String, @Body coin: Coin): Response<Coin>
}