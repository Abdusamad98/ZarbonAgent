package com.example.zarbondistributionagent.data.models.getproduct

data class productFullData(
    val category: Category,
    val id: Int,
    val image: String,
    val name: String,
    val product_type: String,
    val provider: Provider,
    val quantity: Int,
    val unit: String,
    val price:Int,
    val price_id :Int
)