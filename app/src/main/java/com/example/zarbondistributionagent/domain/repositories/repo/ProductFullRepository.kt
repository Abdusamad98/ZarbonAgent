package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.getproduct.productFullData
import kotlinx.coroutines.flow.Flow

interface ProductFullRepository {
    suspend fun productFullData(id:String): Flow<Result<productFullData?>>
}