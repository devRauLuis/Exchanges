package com.devaruluis.exchanges.data.network

import com.devaruluis.exchanges.model.Exchange
import retrofit2.Response
import retrofit2.http.GET

interface CoinPaprikaApiClient {
    @GET("/v1/exchanges/")
    suspend fun getAllExchanges(): Response<List<Exchange>>
}