package com.example.zarbondistributionagent.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.data.models.discountsmodel.DiscountedProduct
import com.example.zarbondistributionagent.data.models.discountsmodel.Discounts
import com.example.zarbondistributionagent.domain.usecases.DiscountedProductsUseCase
import com.example.zarbondistributionagent.domain.usecases.DiscountsUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.DiscountUseCaseImpl
import com.example.zarbondistributionagent.domain.usecases.impl.DiscountedProductsUseCaseImpl
import com.example.zarbondistributionagent.utils.isConnected

class DiscountsPageViewModel : ViewModel() {

    private val discountsUseCase: DiscountsUseCase = DiscountUseCaseImpl()
    val errorDiscountsLiveData: LiveData<Unit> = discountsUseCase.errorDiscountsLiveData
    val progressDiscountsLiveData = MutableLiveData<Boolean>()
    val connectionErrorDiscountsLiveData = MutableLiveData<Unit>()
    val successDiscountsLiveData = MediatorLiveData<List<Discounts>>()

    private val discountedProductsUseCase: DiscountedProductsUseCase =
        DiscountedProductsUseCaseImpl()
    val errorDiscountedProductsLiveData: LiveData<Unit> =
        discountedProductsUseCase.errorDiscountedProductsLiveData
    val progressDiscountedProductsLiveData = MutableLiveData<Boolean>()
    val connectionErrorDiscountedProductsLiveData = MutableLiveData<Unit>()
    val successDiscountedProductsLiveData = MediatorLiveData<List<DiscountedProduct>>()


    init {
        getDiscounts()
        //getDiscountedProducts(1)
    }


    fun getDiscounts() {
        if (isConnected()) {
            progressDiscountsLiveData.value = true
            val liveData = discountsUseCase.getDiscounts()
            successDiscountsLiveData.addSource(liveData) {
                progressDiscountsLiveData.value = false
                successDiscountsLiveData.value = it
                successDiscountsLiveData.removeSource(liveData)
            }
        } else {
            progressDiscountsLiveData.value = false
            connectionErrorDiscountsLiveData.value = Unit
        }

    }

    fun getDiscountedProducts(discountId: Int) {
        if (isConnected()) {
            progressDiscountedProductsLiveData.value = true
            val liveData = discountedProductsUseCase.getDiscountedProducts(discountId.toString())
            successDiscountedProductsLiveData.addSource(liveData) {
                progressDiscountedProductsLiveData.value = false
                successDiscountedProductsLiveData.value = it
                successDiscountedProductsLiveData.removeSource(liveData)
            }
        } else {
            progressDiscountedProductsLiveData.value = false
            connectionErrorDiscountedProductsLiveData.value = Unit
        }
    }
}