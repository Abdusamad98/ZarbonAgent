package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.discountsmodel.DiscountedProduct

interface DiscountedProductsUseCase {
    val errorDiscountedProductsLiveData: LiveData<Unit>
    fun getDiscountedProducts(discountId: String): LiveData<List<DiscountedProduct>>
}