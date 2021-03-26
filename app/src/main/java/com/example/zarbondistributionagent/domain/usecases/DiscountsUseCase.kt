package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.discountsmodel.Discounts

interface DiscountsUseCase {
    val errorDiscountsLiveData : LiveData<Unit>
    fun getDiscounts() : LiveData<List<Discounts>>
}