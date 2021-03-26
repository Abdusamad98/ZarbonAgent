package com.example.zarbondistributionagent.domain.usecases.localusecase

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.reportmodel.ReportHistory

interface ReportsHistoryUseCase {
    val errorLiveData : LiveData<String>
    fun getReports(): LiveData<List<ReportHistory>>
}