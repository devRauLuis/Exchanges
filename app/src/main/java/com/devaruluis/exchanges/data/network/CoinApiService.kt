package com.devaruluis.exchanges.data.network

import com.devaruluis.exchanges.model.Coin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinApiService @Inject constructor(private val api: CoinApiClient) {
    suspend fun getCoins(): List<Coin> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCoins()
            response.body() ?: emptyList()
        }
    }

    suspend fun getCoin(id: String?): Coin? {
        return withContext(Dispatchers.IO) {
            val res = api.getCoin(id ?: "")
            res.body()
        }
    }

    suspend fun createCoin(coin: Coin): Coin? {
        return withContext(Dispatchers.IO) {
            val res = api.createCoin(coin)
            res.body()
        }
    }

    suspend fun deleteCoin(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            val res = api.deleteCoin(id)
            res.isSuccessful
        }
    }

    suspend fun updateCoin(id: String, coin: Coin): Coin? {
        return withContext(Dispatchers.IO) {
            val res = api.updateCoin(id, coin)
            res.body()
        }
    }

}