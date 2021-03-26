package com.example.zarbondistributionagent.ui.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.data.models.getproduct.productFullData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import com.example.zarbondistributionagent.domain.usecases.ProductFullUseCase
import com.example.zarbondistributionagent.domain.usecases.SellProductUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.ProductFullUseCaseImpl
import com.example.zarbondistributionagent.domain.usecases.impl.SellProductUseCaseImpl
import com.example.zarbondistributionagent.utils.isConnected


class SellProductViewModel : ViewModel() {

    private val useCaseProduct: ProductFullUseCase = ProductFullUseCaseImpl()
    val errorProductLiveData :LiveData<Unit> = useCaseProduct.errorProductLiveData
    val progressProductLiveData = MutableLiveData<Boolean>()
    val connectionErrorProductLiveData = MutableLiveData<Unit>()
    val successProductLiveData = MediatorLiveData<productFullData>()


    private val useCase: SellProductUseCase = SellProductUseCaseImpl()
    val errorNotResponseLiveData : LiveData<String> = useCase.errorNotResponseLiveData
    val errorResponseLiveData = MediatorLiveData<String>()
    val progressSellLiveData= MutableLiveData<Boolean>()
    val connectionErrorLiveData = MutableLiveData<Unit>()
    val successLiveData = MediatorLiveData<SellProductResponse>()

    init {
        val f = useCase.errorResponseLiveData
        errorResponseLiveData.addSource(f) {
            progressSellLiveData.value = false
            errorResponseLiveData.value = it
        }
    }


    fun sellProduct(productData: SellProductData) {
        if(isConnected()){
            progressSellLiveData.value = true
            val lvd = useCase.sellProduct(productData)
           // useCase.errorSellLiveData
            successLiveData.addSource(lvd) {
                progressSellLiveData.value = false
                successLiveData.value = it
                successLiveData.removeSource(lvd)
            }
        } else {
            connectionErrorLiveData.value =Unit
        }

    }


    fun getProduct(productId:String) {
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



}