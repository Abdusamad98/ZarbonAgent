package com.example.zarbondistributionagent.domain.usecases.impl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import com.example.zarbondistributionagent.domain.repositories.repo.ClientsRepository
import com.example.zarbondistributionagent.domain.repositories.impl.ClientsRepositoryImpl
import com.example.zarbondistributionagent.domain.usecases.ClientsUseCase
import kotlinx.coroutines.flow.collect

class ClientsUseCaseImpl : ClientsUseCase {
    private val repository: ClientsRepository = ClientsRepositoryImpl()
    override val errorClientsLiveData = MutableLiveData<Unit>()

    override fun getClients(): LiveData<ClientsData> = liveData {
        repository.getClients().collect {
            if (it.isSuccess) {
                emit(it.getOrNull()!!)
            } else {
                errorClientsLiveData.postValue(Unit)
            }
        }


    }
}