package com.example.zarbondistributionagent.domain.usecases

import androidx.lifecycle.LiveData
import com.example.zarbondistributionagent.data.models.loginmodel.LoginData
import com.example.zarbondistributionagent.data.models.loginmodel.LoginResponse

interface LoginUseCase {
    val errorLoginLiveData : LiveData<String>
    fun userLogin(loginData: LoginData) : LiveData<LoginResponse>
}