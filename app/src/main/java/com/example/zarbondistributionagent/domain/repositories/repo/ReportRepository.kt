package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.reportmodel.ReportData
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun reportSend(data:ReportData): Flow<Result<Any?>>
}