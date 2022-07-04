package com.devaruluis.exchanges.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.exchanges.data.network.CoinApiService
import com.devaruluis.exchanges.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class UiState(
    val id: String? = "",
    val name: String? = "",
    val price: Float? = null,
    val img: String? = "",
    val symbol: String? = "",
    val rank: Int? = null,
    val isNew: Boolean? = null,
    val isActive: Boolean? = null,
    val type: String? = "",
    val coinsList: List<Coin> = listOf(),
    val userMessages: List<String> = listOf(),
    var showSnackbar: Boolean = false,
    var snackbarMessage: String? = ""
)

fun UiState.toCoin() = Coin(
    id = id.toString(),
    name = name,
    price = price ?: 0F,
    img = img,
    symbol = symbol,
    rank = rank ?: 0,
    isNew = isNew ?: false,
    isActive = isActive ?: false,
    type = type
)


@HiltViewModel
class CoinsViewModel @Inject constructor(val coinApiService: CoinApiService) :
    ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            uiState = try {
                uiState.copy(coinsList = coinApiService.getCoins().sortedBy { it.rank })
            } catch (ioe: IOException) {
                val messages = listOf(ioe.message.toString())
                uiState.copy(userMessages = messages)
            }
        }
    }

    private var fetchJob: Job? = null

    fun setId(id: String? = "") {
        uiState = uiState.copy(id = id)
    }

    fun setName(name: String? = "") {
        uiState = uiState.copy(name = name)
    }

    fun setPrice(price: Float? = 0F) {
        uiState = uiState.copy(price = price)
    }

    fun setImg(img: String? = "") {
        uiState = uiState.copy(img = img)
    }

    fun setSymbol(symbol: String? = "") {
        uiState = uiState.copy(symbol = symbol)
    }

    fun setRank(rank: Int? = 0) {
        uiState = uiState.copy(rank = rank)
    }

    fun setIsNew(isNew: Boolean? = null) {
        uiState = uiState.copy(isNew = isNew)
    }

    fun setIsActive(isActive: Boolean? = null) {
        uiState = uiState.copy(isActive = isActive)
    }

    fun setType(type: String? = "") {
        uiState = uiState.copy(type = type)
    }

    fun setCoin(coin: Coin?) {
        uiState = uiState.copy(
            id = coin?.id,
            name = coin?.name,
            price = coin?.price,
            img = coin?.img,
            symbol = coin?.symbol,
            isNew = coin?.isNew,
            isActive = coin?.isActive,
            rank = coin?.rank,
            type = coin?.type
        )
    }


    fun showSnackbar(message: String) {
        uiState = uiState.copy(showSnackbar = true, snackbarMessage = message)
    }

    fun dismissSnackbar() {
        uiState = uiState.copy(showSnackbar = false, snackbarMessage = null)
    }

    fun update(coin: Coin) {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            try {
                val updatedCoin = coinApiService.updateCoin(coin.id, coin)
                setCoin(updatedCoin)
                showSnackbar("Exito")
            } catch (ioe: IOException) {
                showSnackbar("Error")
            }
        }
    }

    fun create(coin: Coin) {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            try {
                val newCoin = coinApiService.createCoin(coin)
                setCoin(newCoin)
                showSnackbar("Exito")
            } catch (ioe: IOException) {
                showSnackbar("Error")
            }
        }
    }

    fun save() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            val coin = uiState.toCoin()

            if (!(coin.id != null && coin.id.length > 3)) {
                showSnackbar("El id no puede ser tan corto")
            } else if (!(coin.name != null && coin.name.length > 3)) {
                showSnackbar("El nombre no puede ser tan corto")
            } else if (!(coin.price != null && coin.price > 0)) {
                showSnackbar("El precio debe ser mayor a 0")
            } else {
                val exists = coinApiService.getCoin(coin.id) != null
                if (exists) update(coin) else create(coin)
            }
        }
    }

    fun find() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            try {
                val coin = coinApiService.getCoin(uiState.id)
                if (coin != null) {
                    setCoin(coin)
                    showSnackbar("Encontrado")
                } else {
                    showSnackbar("No encontrado con id: " + uiState.id)
                }
            } catch (ioe: IOException) {
                val messages = listOf(ioe.message.toString())
                uiState = uiState.copy(userMessages = messages)
            }
        }
    }

    fun delete() {
        fetchJob?.cancel()

        fetchJob = viewModelScope.launch {
            if (!uiState.id.isNullOrEmpty()) {
                try {
                    val deleted = coinApiService.deleteCoin(uiState.id!!)
                    if (deleted) {
                        showSnackbar("Eliminado con id: " + uiState.id)
                        new()
                    } else {
                        showSnackbar("Error")
                    }
                } catch (ioe: IOException) {
                    val messages = listOf(ioe.message.toString())
                    uiState = uiState.copy(userMessages = messages)

                }
            }
        }
    }

    fun new() {
        viewModelScope.launch {
            uiState = UiState()
        }
    }

}