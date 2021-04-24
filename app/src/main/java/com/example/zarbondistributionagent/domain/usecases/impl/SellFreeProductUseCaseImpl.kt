package com.example.zarbondistributionagent.domain.usecases.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.sellmodel.FreeProductData
import com.example.zarbondistributionagent.domain.repositories.impl.SellFreeProductRepoImpl
import com.example.zarbondistributionagent.domain.repositories.repo.SellFreeProductRepo
import com.example.zarbondistributionagent.domain.usecases.SellFreeProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class SellFreeProductUseCaseImpl : SellFreeProductUseCase {
    private val repository: SellFreeProductRepo = SellFreeProductRepoImpl()
    override val errorLoginLiveData = MutableLiveData<String>()

    override fun sellFreeProduct(freeProductData: FreeProductData): LiveData<Any> =
        liveData(Dispatchers.IO) {
            repository.sellFreeProduct(freeProductData).collect {
                if (it.isSuccess) emit(it.getOrNull()!!)
                else errorLoginLiveData.postValue("Error")
            }
        }

}