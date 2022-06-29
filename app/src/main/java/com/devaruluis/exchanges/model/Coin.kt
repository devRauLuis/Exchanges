package com.devaruluis.exchanges.model

data class Coin(
    val id: String,
    val name: String?,
    val price: Float,
    val img: String?,
    val symbol: String?,
    val rank: Int,
    val isNew: Boolean,
    val isActive: Boolean,
    val type: String?
)
