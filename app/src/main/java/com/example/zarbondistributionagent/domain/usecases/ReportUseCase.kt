package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.reportmodel.ReportData

interface ReportUseCase {
    val errorReportLiveData : LiveData<String>
    fun sendReport(reportData: ReportData) : LiveData<Any>
}