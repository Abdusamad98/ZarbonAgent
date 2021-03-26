package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.salarymodel.SalaryData
import kotlinx.coroutines.flow.Flow

interface SalaryRepository {
    suspend fun getSalary(): Flow<Result<Pair<Int, SalaryData?>>>
}