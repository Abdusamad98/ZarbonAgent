package com.example.zarbondistributionagent.data.models.planmodel

data class Product(
    val category: Int,
    val id: Int,
    val name: String,
    val product_type: String,
    val provider: Int,
    val unit: String
)