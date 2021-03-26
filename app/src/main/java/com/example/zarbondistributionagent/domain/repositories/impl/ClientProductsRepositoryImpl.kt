package com.example.zarbondistributionagent.domain.repositories.impl
import com.example.zarbondistributionagent.data.models.clientmodel.clientproducts.ClientProducts
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.ClientProductsApi
import com.example.zarbondistributionagent.domain.repositories.repo.ClientProductsRepository
import com.example.zarbondistributionagent.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClientProductsRepositoryImpl : ClientProductsRepository {
    private val api = ApiClient.retrofit.create(ClientProductsApi::class.java)
    override suspend fun getClientProducts(clientId: String): Flow<Result<List<ClientProducts>?>> = flow {

        try {
            val response = api.getClientProducts("order/client-sell-order-list/${clientId}/")
            if (response.code() == 200) {
                emit(Result.success(response.body()))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
            log("TTT", "exception = $e" + "Xatolik!")
        }
    }


}