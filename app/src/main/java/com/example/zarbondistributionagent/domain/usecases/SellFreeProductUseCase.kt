package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.sellmodel.FreeProductData

interface SellFreeProductUseCase {
    val errorLoginLiveData: LiveData<String>
    fun sellFreeProduct(freeProductData: FreeProductData): LiveData<Any>
}