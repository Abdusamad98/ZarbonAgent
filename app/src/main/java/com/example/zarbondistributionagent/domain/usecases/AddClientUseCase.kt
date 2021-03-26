package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.clientmodel.AddClientData

interface AddClientUseCase {
    val errorAddClientLiveData: LiveData<String>
    var locationSt : String?
    val locationLiveData : LiveData<String?>
    fun addClient(addClientData: AddClientData): LiveData<Any>
}

//60.25561;42.1511556