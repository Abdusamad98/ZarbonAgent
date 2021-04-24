package com.example.zarbondistributionagent.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.domain.usecases.ClientsUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.ClientsUseCaseImpl
import com.example.zarbondistributionagent.utils.isConnected

class ClientPageViewModel:ViewModel() {

    private val useCase: ClientsUseCase = ClientsUseCaseImpl()
    val errorCategoriesLiveData : LiveData<Unit> = useCase.errorClientsLiveData
    val progressLiveData= MutableLiveData<Boolean>()
    val connectionErrorLiveData = MutableLiveData<Unit>()
    val successLiveData = MediatorLiveData<ClientsData>()


    init {
       getClients()
    }


    fun getClients() {
        if(isConnected()){
            progressLiveData.value = true
            val liveData = useCase.getClients()
            successLiveData.addSource(liveData) {
                progressLiveData.value = false
                successLiveData.value = it
                successLiveData.removeSource(liveData)
            }
        } else {
            connectionErrorLiveData.value =Unit
        }

    }

    val closeLiveData = MutableLiveData<Unit>()
    fun closeSearch(){
        closeLiveData.value = Unit
    }
}