package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.planmodel.PlanData

interface PlanUseCase {
    val errorPlanLiveData : LiveData<Unit>
    fun getPlans() : LiveData<List<PlanData>>
}