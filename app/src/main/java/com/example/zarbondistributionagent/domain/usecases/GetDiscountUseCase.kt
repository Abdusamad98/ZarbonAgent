package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData

interface GetDiscountUseCase {
    val errorNotResponseLiveData: LiveData<String>
    val errorResponseLiveData: LiveData<String>
    fun getPermittedDiscount(): LiveData<PermittedDiscountData>
}