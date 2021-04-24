package com.example.zarbondistributionagent.domain.repositories.impl

import android.util.Log
import com.example.zarbondistributionagent.data.models.sellmodel.FreeProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import com.example.zarbondistributionagent.data.source.local.TokenSaver
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.FreeProductApi
import com.example.zarbondistributionagent.data.source.remote.retrofit.SellProductApiInterface
import com.example.zarbondistributionagent.domain.repositories.repo.SellFreeProductRepo
import com.example.zarbondistributionagent.domain.repositories.repo.SellProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SellFreeProductRepoImpl : SellFreeProductRepo {
    private val api = ApiClient.retrofit.create(FreeProductApi::class.java)

    override suspend fun sellFreeProduct(productData: FreeProductData):
            Flow<Result<Any>> = flow {
        try {
            val response = api.sellFreeProduct("application/json",productData.quantity.toString(),TokenSaver.getAgentId(),productData.product,productData.sell_orders)
            if(response.code() == 201) {
                emit(Result.success(response.body()!!))
            }
        } catch (e : Exception) {
            Log.d("SELL","exception = $e")
        }
    }
}