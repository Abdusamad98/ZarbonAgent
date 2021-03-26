package com.example.zarbondistributionagent.domain.repositories.impl

import com.example.zarbondistributionagent.data.models.planmodel.PlanDetail
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.PlanDetailApiInterface
import com.example.zarbondistributionagent.domain.repositories.repo.PlanDetailRepository
import com.example.zarbondistributionagent.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlanDetailRepositoryImpl : PlanDetailRepository {
    private val api = ApiClient.retrofit.create(PlanDetailApiInterface::class.java)

    override suspend fun getPlansDetail(planId: String):
            Flow<Result<Pair<Int, List<PlanDetail>?>>> = flow {
        try {
            val response =
                api.getPlanDetails("plan/plan-item-list/$planId/${TokenSaver.getAgentId()}/")
            if (response.code() == 200) {
                emit(Result.success(Pair(200,response.body())))
            }
            else if(response.code() == 404||response.code() == 500){
                emit(Result.success(Pair(404,null)))
            }

        } catch (e: Exception) {
            log("TTT", "exception = $e" + "Xatolik!")
        }
    }
}


