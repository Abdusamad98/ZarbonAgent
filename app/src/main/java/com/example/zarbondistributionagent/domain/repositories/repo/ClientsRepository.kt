package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import kotlinx.coroutines.flow.Flow

interface ClientsRepository {
    suspend fun getClients(filter:String): Flow<Result<List<ClientsData>?>>
}

