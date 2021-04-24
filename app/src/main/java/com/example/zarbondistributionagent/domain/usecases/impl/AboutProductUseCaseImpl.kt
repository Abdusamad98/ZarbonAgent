package com.example.zarbondistributionagent.domain.usecases.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.zarbondistributionagent.data.models.productsmodel.AboutProduct
import com.example.zarbondistributionagent.domain.repositories.impl.AboutProductRepositoryImpl
import com.example.zarbondistributionagent.domain.repositories.repo.AboutProductRepository
import com.example.zarbondistributionagent.domain.usecases.AboutProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class AboutProductUseCaseImpl : AboutProductUseCase {
    private val repository: AboutProductRepository = AboutProductRepositoryImpl()
    override val errorNotResponseLiveData = MutableLiveData<String>()
    override val errorResponseLiveData = MutableLiveData<String>()

    override fun getAboutProduct(id: Int): LiveData<AboutProduct> =
        liveData(Dispatchers.IO) {
            repository.getAboutProduct(id).collect {
                if (it.isSuccess) {
                    it.getOrNull()?.let { pair ->
                        if (pair.first == 200) pair.second?.let { it1 -> emit(it1) }
                        if (pair.first == 404) errorResponseLiveData.postValue("Ma'lumot topilmadi!")
                    }
                } else {
                    errorNotResponseLiveData.postValue("Error")
                }
            }
        }

}