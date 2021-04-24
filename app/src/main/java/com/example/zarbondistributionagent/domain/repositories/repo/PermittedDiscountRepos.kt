package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData
import com.example.zarbondistributionagent.data.models.salarymodel.SalaryData
import kotlinx.coroutines.flow.Flow

interface PermittedDiscountRepos {
    suspend fun getPermittedDiscount(): Flow<Result<Pair<Int, PermittedDiscountData?>>>
}