package com.example.zarbondistributionagent.domain.repositories.repo

import com.example.zarbondistributionagent.data.models.loginmodel.LoginData
import com.example.zarbondistributionagent.data.models.loginmodel.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun userLogin(loginData: LoginData) : Flow<Result<LoginResponse?>>
}