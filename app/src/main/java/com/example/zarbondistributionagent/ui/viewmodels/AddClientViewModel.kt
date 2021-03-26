package com.example.zarbondistributionagent.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.data.models.clientmodel.AddClientData
import com.example.zarbondistributionagent.domain.usecases.AddClientUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.AddClientUseCaseImpl
import com.example.zarbondistributionagent.utils.isConnected

class AddClientViewModel : ViewModel() {

    private val useCase: AddClientUseCase = AddClientUseCaseImpl.getUseCase()
    val errorAddClientLiveData: LiveData<String> = useCase.errorAddClientLiveData
    val progressLiveData = MutableLiveData<Boolean>()
    val connectionErrorLiveData = MutableLiveData<Unit>()
    val successLiveData = MediatorLiveData<Any>()
    val locationLiveData = MediatorLiveData<String>()

    init {
        val f = useCase.locationLiveData
        locationLiveData.addSource(f) {
            if (it != null) {
                locationLiveData.value = it
                useCase.locationSt = null
            }
        }
    }
    fun addClient(addClientData: AddClientData) {
        if (isConnected()) {
            progressLiveData.value = true
            val lvd = useCase.addClient(addClientData)
            successLiveData.addSource(lvd) {
                progressLiveData.value = false
                successLiveData.value = it
                successLiveData.removeSource(lvd)
            }
        } else {
            connectionErrorLiveData.value = Unit
        }

    }

}