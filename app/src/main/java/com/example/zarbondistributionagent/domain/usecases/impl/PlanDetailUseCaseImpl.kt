package com.example.zarbondistributionagent.domain.usecases.impl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.planmodel.PlanDetail
import com.example.zarbondistributionagent.domain.repositories.repo.PlanDetailRepository
import com.example.zarbondistributionagent.domain.repositories.impl.PlanDetailRepositoryImpl
import com.example.zarbondistributionagent.domain.usecases.PlanDetailUseCase
import kotlinx.coroutines.flow.collect


class PlanDetailUseCaseImpl : PlanDetailUseCase {
    override val errorEmptyResponseLiveData = MutableLiveData<String>()

    private val repository: PlanDetailRepository = PlanDetailRepositoryImpl()
    override val errorPlanDetailLiveData = MutableLiveData<Unit>()
    override fun getPlansDetail(planId:String): LiveData<List<PlanDetail>> = liveData {
        repository.getPlansDetail(planId).collect {
            if (it.isSuccess) {
                it.getOrNull()?.let { pair ->
                    if (pair.first == 200) pair.second?.let { it1 -> emit(it1) }
                    if (pair.first == 404) errorEmptyResponseLiveData.postValue("Ma'lumot mavjud emas!")

                }
            } else {
                errorPlanDetailLiveData.postValue(Unit)
            }
        }
    }

}

