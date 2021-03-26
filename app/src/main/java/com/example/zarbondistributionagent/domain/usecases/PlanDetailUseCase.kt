package com.example.zarbondistributionagent.domain.usecases


import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.planmodel.PlanDetail

interface PlanDetailUseCase {
    val errorEmptyResponseLiveData : LiveData<String>
    val errorPlanDetailLiveData : LiveData<Unit>
    fun getPlansDetail(planId:String) : LiveData<List<PlanDetail>>
}