package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.productsmodel.AboutProduct
import kotlinx.coroutines.flow.Flow

interface AboutProductRepository {
    suspend fun getAboutProduct(id:Int)
            : Flow<Result<Pair<Int, AboutProduct?>>>
}