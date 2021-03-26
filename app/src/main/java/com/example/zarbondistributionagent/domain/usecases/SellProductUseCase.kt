package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse

interface SellProductUseCase {
    val errorNotResponseLiveData : LiveData<String>
    val errorResponseLiveData : LiveData<String>


    fun sellProduct(productData: SellProductData) : LiveData<SellProductResponse>
}