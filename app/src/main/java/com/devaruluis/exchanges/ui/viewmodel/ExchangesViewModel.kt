package com.devaruluis.exchanges.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devaruluis.exchanges.data.network.CoinPaprikaService
import com.devaruluis.exchanges.model.Exchange
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

data class UiState(
    val exchangesList: List<Exchange> = listOf(),
    val userMessages: List<String> = listOf(),
)

@HiltViewModel
class ExchangesViewModel @Inject constructor(val coinPaprikaService: CoinPaprikaService) :
    ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            uiState = try {
                uiState.copy(exchangesList = coinPaprikaService.getExchanges())
            } catch (ioe: IOException) {
                val messages = listOf(ioe.message.toString())
                uiState.copy(userMessages = messages)
            }
        }
    }


}