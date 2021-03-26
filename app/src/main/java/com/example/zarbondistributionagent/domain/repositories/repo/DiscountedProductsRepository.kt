package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.discountsmodel.DiscountedProduct
import kotlinx.coroutines.flow.Flow

interface DiscountedProductsRepository {
    suspend fun getDiscountedProducts(discountId:String): Flow<Result<List<DiscountedProduct>?>>
}