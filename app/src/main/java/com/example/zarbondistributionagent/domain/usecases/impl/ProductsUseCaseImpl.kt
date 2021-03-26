package com.example.zarbondistributionagent.domain.usecases.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.productsmodel.ProductData
import com.example.zarbondistributionagent.domain.repositories.repo.ProductsRepository
import com.example.zarbondistributionagent.domain.repositories.impl.ProductsRepositoryIml
import com.example.zarbondistributionagent.domain.usecases.ProductsUseCase
import kotlinx.coroutines.flow.collect

class ProductsUseCaseImpl : ProductsUseCase {
    private val repository: ProductsRepository = ProductsRepositoryIml()
    override val errorProductsLiveData = MutableLiveData<Unit>()
    override fun getProducts(categoryId: String): LiveData<List<ProductData>> = liveData {
    repository.getProducts(categoryId).collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorProductsLiveData.postValue(Unit)
            }
        }
    }
}