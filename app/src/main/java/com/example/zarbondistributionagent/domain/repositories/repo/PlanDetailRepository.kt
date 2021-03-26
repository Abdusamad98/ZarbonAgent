package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.planmodel.PlanDetail
import kotlinx.coroutines.flow.Flow

interface PlanDetailRepository {
    suspend fun getPlansDetail(planId:String): Flow<Result<Pair<Int, List<PlanDetail>?>>>
}