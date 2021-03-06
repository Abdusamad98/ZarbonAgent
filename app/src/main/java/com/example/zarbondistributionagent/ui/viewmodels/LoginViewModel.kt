package com.example.zarbondistributionagent.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.data.models.loginmodel.LoginData
import com.example.zarbondistributionagent.data.models.loginmodel.LoginResponse
import com.example.zarbondistributionagent.domain.usecases.LoginUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.LoginUseCaseImpl
import com.example.zarbondistributionagent.utils.isConnected

class LoginViewModel : ViewModel() {
    private val useCase: LoginUseCase = LoginUseCaseImpl()
    val errorLoginLiveData : LiveData<String> = useCase.errorLoginLiveData
    val progressLiveData= MutableLiveData<Boolean>()
    val connectionErrorLiveData = MutableLiveData<Unit>()
    val successLiveData = MediatorLiveData<LoginResponse>()

    fun login(userName : String, password:String) {
        if(isConnected()){
            progressLiveData.value = true
            val lvd = useCase.userLogin(LoginData(userName,password))
            successLiveData.addSource(lvd) {
                progressLiveData.value = false
                successLiveData.value = it
                successLiveData.removeSource(lvd)
                Log.d("LLL",it.toString())
            }
        } else {
            connectionErrorLiveData.value =Unit
        }

    }
}