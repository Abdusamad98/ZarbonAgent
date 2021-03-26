package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.historymodel.SoldProductHistory

interface SoldHistoryUseCase {
    val errorHistoryLiveData : LiveData<Unit>
    fun soldHistory() : LiveData<List<SoldProductHistory>>
}