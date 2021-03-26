package com.example.zarbondistributionagent.domain.usecases.impl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.discountsmodel.DiscountedProduct
import com.example.zarbondistributionagent.domain.repositories.repo.DiscountedProductsRepository
import com.example.zarbondistributionagent.domain.repositories.impl.DiscountedProductsRepositoryImpl
import com.example.zarbondistributionagent.domain.usecases.DiscountedProductsUseCase
import kotlinx.coroutines.flow.collect

class DiscountedProductsUseCaseImpl : DiscountedProductsUseCase {

    private val repository: DiscountedProductsRepository = DiscountedProductsRepositoryImpl()
    override val errorDiscountedProductsLiveData = MutableLiveData<Unit>()

    override fun getDiscountedProducts(discountId: String): LiveData<List<DiscountedProduct>> = liveData {
        repository.getDiscountedProducts(discountId).collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorDiscountedProductsLiveData.postValue(Unit)
            }
        }
    }
}