package com.example.zarbondistributionagent.data.models.sellmodel

data class SellProductData(
    val quantity: String,
    val client: Int,
    val product: Int,
    val discount: Int,
    val price: Int
)
