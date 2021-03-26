package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.clientmodel.AddClientData
import kotlinx.coroutines.flow.Flow

interface AddClientRepository {
    suspend fun addClient(data: AddClientData): Flow<Result<Any?>>
}

