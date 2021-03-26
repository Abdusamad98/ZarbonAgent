package com.example.zarbondistributionagent.domain.repositories.impl

import com.example.zarbondistributionagent.data.models.historymodel.SoldProductHistory
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.SoldHistoryApi
import com.example.zarbondistributionagent.domain.repositories.repo.SoldHistoryRepository
import com.example.zarbondistributionagent.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SoldHistoryRepositoryImpl : SoldHistoryRepository {
    private val api = ApiClient.retrofit.create(SoldHistoryApi::class.java)
    override suspend fun soldHistory(): Flow<Result<List<SoldProductHistory>?>> = flow {
        try {
            val response = api.soldHistory("order/agent-order-list/${TokenSaver.getAgentId()}/")
            if (response.code() == 200) {
                emit(Result.success(response.body()))
                log(response.body().toString(), "QQQ")
            }

        } catch (e: Exception) {
            log("TTT", "exception = $e" + "Xatolik!")
        }
    }


}