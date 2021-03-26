package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.productsmodel.ProductData

interface ProductsUseCase {
    val errorProductsLiveData: LiveData<Unit>
    fun getProducts(categoryId: String): LiveData<List<ProductData>>
}