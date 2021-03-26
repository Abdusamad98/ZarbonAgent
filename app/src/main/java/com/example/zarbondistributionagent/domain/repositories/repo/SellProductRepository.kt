package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import kotlinx.coroutines.flow.Flow

interface SellProductRepository {
    suspend fun sellProduct(productData: SellProductData)
    : Flow<Result<Pair<Int,SellProductResponse?>>>
}