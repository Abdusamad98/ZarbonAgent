package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.historymodel.SoldProductHistory
import kotlinx.coroutines.flow.Flow

interface SoldHistoryRepository {
    suspend fun soldHistory()
            : Flow<Result<List<SoldProductHistory>?>>
}