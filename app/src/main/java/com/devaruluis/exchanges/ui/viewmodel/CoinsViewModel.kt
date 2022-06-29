package com.devaruluis.exchanges.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.exchanges.data.network.CoinApiService
import com.devaruluis.exchanges.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class UiState(
    val coinsList: List<Coin> = listOf(),
    val userMessages: List<String> = listOf(),
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


}