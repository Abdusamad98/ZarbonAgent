package com.example.zarbondistributionagent.domain.usecases.impl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.discountsmodel.Discounts
import com.example.zarbondistributionagent.domain.repositories.repo.DiscountsRepository
import com.example.zarbondistributionagent.domain.repositories.impl.DiscountsRepositoryImpl
import com.example.zarbondistributionagent.domain.usecases.DiscountsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class DiscountUseCaseImpl : DiscountsUseCase {

    private val repository: DiscountsRepository = DiscountsRepositoryImpl()
    override val errorDiscountsLiveData = MutableLiveData<Unit>()

    override fun getDiscounts(): LiveData<List<Discounts>> = liveData(Dispatchers.IO) {
        repository.getDiscounts().collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorDiscountsLiveData.postValue(Unit)
            }
        }
    }
}
