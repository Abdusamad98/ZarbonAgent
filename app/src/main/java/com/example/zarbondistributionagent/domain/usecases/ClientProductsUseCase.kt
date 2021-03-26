package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.clientmodel.clientproducts.ClientProducts

interface ClientProductsUseCase {
    val errorClientProductsLiveData: LiveData<Unit>
    fun getClientProducts(clientId: String): LiveData<List<ClientProducts>>
}