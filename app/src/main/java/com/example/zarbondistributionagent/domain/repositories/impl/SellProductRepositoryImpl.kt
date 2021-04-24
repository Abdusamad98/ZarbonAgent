package com.example.zarbondistributionagent.domain.repositories.impl

import android.util.Log
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductData
import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.SellProductApiInterface
import com.example.zarbondistributionagent.domain.repositories.repo.SellProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SellProductRepositoryImpl : SellProductRepository {
    private val api = ApiClient.retrofit.create(SellProductApiInterface::class.java)

    override suspend fun sellProduct(productData: SellProductData):
            Flow<Result<Pair<Int,SellProductResponse?>>> = flow {
        try {
            val response = api.sellProduct("application/json","agent",productData.quantity,productData.client,productData.product,productData.discount,productData.price)
            if(response.code() == 201) {
                emit(Result.success(Pair(201,response.body())))
            }
            else if(response.code() == 400){
                emit(Result.success(Pair(400,null)))
            }
        } catch (e : Exception) {
            Log.d("SELL","exception = $e")
        }
    }
}