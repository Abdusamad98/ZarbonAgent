package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.planmodel.PlanData
import kotlinx.coroutines.flow.Flow

interface PlanRepository {
    suspend fun getPlans(): Flow<Result<List<PlanData>?>>
}