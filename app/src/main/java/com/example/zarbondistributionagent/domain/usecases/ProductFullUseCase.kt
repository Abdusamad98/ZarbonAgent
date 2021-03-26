package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.getproduct.productFullData

interface ProductFullUseCase {
    val errorProductLiveData: LiveData<Unit>
    fun getProduct(productId: String): LiveData<productFullData>
}