package com.example.zarbondistributionagent.domain.usecases.impl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData
import com.example.zarbondistributionagent.domain.repositories.impl.PermittedDiscountReposImpl
import com.example.zarbondistributionagent.domain.repositories.repo.PermittedDiscountRepos
import com.example.zarbondistributionagent.domain.usecases.GetDiscountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect


class DiscountUseCaseImpl : GetDiscountUseCase {
    private val repository: PermittedDiscountRepos = PermittedDiscountReposImpl()
    override val errorNotResponseLiveData = MutableLiveData<String>()
    override val errorResponseLiveData = MutableLiveData<String>()

    override fun getPermittedDiscount(): LiveData<PermittedDiscountData> =
        liveData(Dispatchers.IO) {
            repository.getPermittedDiscount().collect {
                if (it.isSuccess) {
                    it.getOrNull()?.let { pair ->
                        if (pair.first == 200) pair.second?.let { it1 -> emit(it1) }
                        if (pair.first == 500) errorResponseLiveData.postValue("Sizga Discount Yuq!")
                    }
                } else {
                    errorNotResponseLiveData.postValue("Error")
                }
            }
        }

}
