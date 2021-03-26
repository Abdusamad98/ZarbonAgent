package com.example.zarbondistributionagent.domain.repositories.impl

import android.util.Log
import com.example.zarbondistributionagent.data.models.salarymodel.SalaryData
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.SalaryApiInterface
import com.example.zarbondistributionagent.domain.repositories.repo.SalaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SalaryRepositoryImpl : SalaryRepository {
    private val api = ApiClient.retrofit.create(SalaryApiInterface::class.java)
    override suspend fun getSalary():
            Flow<Result<Pair<Int, SalaryData?>>> = flow {
        try {
            val response = api.getSalary("salary/agent-flexible-salary/${TokenSaver.getAgentId()}/")
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