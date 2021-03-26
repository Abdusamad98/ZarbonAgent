package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData

interface ClientsUseCase {
    val errorClientsLiveData : LiveData<Unit>
    fun getClients(filter:String) : LiveData<List<ClientsData>>
}