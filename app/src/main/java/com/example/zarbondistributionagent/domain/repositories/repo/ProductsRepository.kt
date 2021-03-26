package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.productsmodel.ProductData
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(categoryId:String): Flow<Result<List<ProductData>?>>
}