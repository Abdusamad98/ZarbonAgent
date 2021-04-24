package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.sellmodel.FreeProductData
import kotlinx.coroutines.flow.Flow

interface SellFreeProductRepo {
    suspend fun sellFreeProduct(productData: FreeProductData)
            : Flow<Result<Any>>
}