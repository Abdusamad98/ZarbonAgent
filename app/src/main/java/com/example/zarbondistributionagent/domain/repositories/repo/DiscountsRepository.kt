package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.discountsmodel.Discounts
import kotlinx.coroutines.flow.Flow

interface DiscountsRepository {
    suspend fun getDiscounts() : Flow<Result<List<Discounts>?>>
}