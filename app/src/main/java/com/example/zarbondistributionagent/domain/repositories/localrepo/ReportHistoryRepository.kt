package com.example.zarbondistributionagent.domain.repositories.localrepo

import com.example.zarbondistributionagent.data.models.reportmodel.ReportHistory
import kotlinx.coroutines.flow.Flow

interface ReportHistoryRepository {
    suspend fun getReports(): Flow<Result<List<ReportHistory>?>>
}