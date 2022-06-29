package com.devaruluis.exchanges.data.network

import com.devaruluis.exchanges.model.Coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinApiClient {
    @GET("/api/coins")
    suspend fun getAllCoins(): Response<List<Coin>>
}