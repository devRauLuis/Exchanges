package com.devaruluis.exchanges.data.network

import com.devaruluis.exchanges.model.Exchange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinPaprikaService @Inject constructor(private val api: CoinPaprikaApiClient) {
    suspend fun getExchanges(): List<Exchange> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllExchanges()
            response.body() ?: emptyList()
        }
    }
}