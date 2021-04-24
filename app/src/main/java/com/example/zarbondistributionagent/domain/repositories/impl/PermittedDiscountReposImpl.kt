package com.example.zarbondistributionagent.domain.repositories.impl

import android.util.Log
import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData
import com.example.zarbondistributionagent.data.models.salarymodel.SalaryData
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.PermittedDiscount
import com.example.zarbondistributionagent.data.source.remote.retrofit.SalaryApiInterface
import com.example.zarbondistributionagent.domain.repositories.repo.PermittedDiscountRepos
import com.example.zarbondistributionagent.domain.repositories.repo.SalaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PermittedDiscountReposImpl : PermittedDiscountRepos {
    private val api = ApiClient.retrofit.create(PermittedDiscount::class.java)
    override suspend fun getPermittedDiscount():
            Flow<Result<Pair<Int, PermittedDiscountData?>>> = flow {
        try {
            val response = api.getPermittedDiscount("expense_discount/agent-discount/${TokenSaver.getAgentId()}/")
            val code = response.code()
            if (code == 200) {
                emit(Result.success(Pair(200, response.body())))
            } else if (code == 500 || code == 400 || code == 404) {
                emit(Result.success(Pair(500, null)))
            }
        } catch (e: Exception) {
            Log.d("SELL", "exception = $e")
        }
    }
}