package com.example.zarbondistributionagent.domain.repositories.impl

import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.ClientsApiInterface
import com.example.zarbondistributionagent.domain.repositories.repo.ClientsRepository
import com.example.zarbondistributionagent.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClientsRepositoryImpl : ClientsRepository {
    private val api = ApiClient.retrofit.create(ClientsApiInterface::class.java)
    override suspend fun getClients(): Flow<Result<ClientsData?>> = flow {
        try {
            val response = api.getClientList("client/get-agent-clients/${TokenSaver.getAgentId()}/")
            if (response.code() == 200) {
                emit(Result.success(response.body()))
                log(response.body().toString(), "QQQ")
            }

        } catch (e: Exception) {
          //emit(Result.failure(e))
            log("TTTD", "exception = $e" + "Xatolik!")
        }
    }

}