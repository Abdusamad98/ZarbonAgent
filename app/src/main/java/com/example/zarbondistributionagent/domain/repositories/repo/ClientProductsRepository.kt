package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.clientmodel.clientproducts.ClientProducts
import kotlinx.coroutines.flow.Flow

interface ClientProductsRepository {
    suspend fun getClientProducts(clientId: String): Flow<Result<List<ClientProducts>?>>
}