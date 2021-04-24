package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.productsmodel.AboutProduct

interface AboutProductUseCase {
    val errorNotResponseLiveData: LiveData<String>
    val errorResponseLiveData: LiveData<String>


    fun getAboutProduct(id: Int): LiveData<AboutProduct>
}