package com.example.zarbondistributionagent.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData
import com.example.zarbondistributionagent.data.models.getproduct.productFullData
import com.example.zarbondistributionagent.data.models.sellmodel.FreeProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import com.example.zarbondistributionagent.domain.usecases.GetDiscountUseCase
import com.example.zarbondistributionagent.domain.usecases.ProductFullUseCase
import com.example.zarbondistributionagent.domain.usecases.SellFreeProductUseCase
import com.example.zarbondistributionagent.domain.usecases.SellProductUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.DiscountUseCaseImpl
import com.example.zarbondistributionagent.domain.usecases.impl.ProductFullUseCaseImpl
import com.example.zarbondistributionagent.domain.usecases.impl.SellFreeProductUseCaseImpl
import com.example.zarbondistributionagent.domain.usecases.impl.SellProductUseCaseImpl
import com.example.zarbondistributionagent.utils.isConnected


class SellProductViewModel : ViewModel() {

    private val useCaseProduct: ProductFullUseCase = ProductFullUseCaseImpl()
    val errorProductLiveData: LiveData<Unit> = useCaseProduct.errorProductLiveData
    val progressProductLiveData = MutableLiveData<Boolean>()
    val connectionErrorProductLiveData = MutableLiveData<Unit>()
    val successProductLiveData = MediatorLiveData<productFullData>()


    private val useCase: SellProductUseCase = SellProductUseCaseImpl()
    val errorNotResponseLiveData: LiveData<String> = useCase.errorNotResponseLiveData
    val errorResponseLiveData = MediatorLiveData<String>()
    val progressSellLiveData = MutableLiveData<Boolean>()
    val connectionErrorLiveData = MutableLiveData<Unit>()
    val successLiveData = MediatorLiveData<SellProductResponse>()


    private val useCaseDiscount: GetDiscountUseCase = DiscountUseCaseImpl()
    val errorNotResponseLiveDataDiscount: LiveData<String> =
        useCaseDiscount.errorNotResponseLiveData
    val errorResponseLiveDataDiscount = MediatorLiveData<String>()
    val progressSellLiveDataDiscount = MutableLiveData<Boolean>()
    val connectionErrorLiveDataDiscount = MutableLiveData<Unit>()
    val successLiveDataDiscount = MediatorLiveData<PermittedDiscountData>()


    private val useCaseFreeProduct: SellFreeProductUseCase = SellFreeProductUseCaseImpl()
    val errorLoginLiveDataFreeProduct: LiveData<String> = useCaseFreeProduct.errorLoginLiveData
    val progressLiveDataFreeProduct = MutableLiveData<Boolean>()
    val connectionErrorLiveDataFreeProduct = MutableLiveData<Unit>()
    val successFreeProduct = MediatorLiveData<Any>()


    fun sellFreeProducts(freeProductData: FreeProductData) {
        if (isConnected()) {
            progressLiveDataFreeProduct.value = true
            val lvd = useCaseFreeProduct.sellFreeProduct(freeProductData)
            successFreeProduct.addSource(lvd) {
                progressLiveDataFreeProduct.value = false
                successFreeProduct.value = it
                successFreeProduct.removeSource(lvd)
            }
        } else {
            connectionErrorLiveDataFreeProduct.value = Unit
        }

    }


    fun sellProduct(productData: SellProductData) {
        if (isConnected()) {
            progressSellLiveData.value = true
            val lvd = useCase.sellProduct(productData)
            successLiveData.addSource(lvd) {
                progressSellLiveData.value = false
                successLiveData.value = it
                successLiveData.removeSource(lvd)
            }
        } else {
            connectionErrorLiveDataDiscount.value = Unit
        }

    }


    fun getProduct(productId: String) {
        if (isConnected()) {
            progressProductLiveData.value = true
            val lvd = useCaseProduct.getProduct(productId)
            successProductLiveData.addSource(lvd) {
                progressProductLiveData.value = false
                successProductLiveData.value = it
                successProductLiveData.removeSource(lvd)
            }
        } else {
            connectionErrorProductLiveData.value = Unit
        }

    }

    fun getDiscount() {
        if (isConnected()) {
            progressSellLiveDataDiscount.value = true
            val lvd = useCaseDiscount.getPermittedDiscount()
            successLiveDataDiscount.addSource(lvd) {
                progressSellLiveDataDiscount.value = false
                successLiveDataDiscount.value = it
                successLiveDataDiscount.removeSource(lvd)
            }
        } else {
            connectionErrorLiveDataDiscount.value = Unit
        }

    }

}