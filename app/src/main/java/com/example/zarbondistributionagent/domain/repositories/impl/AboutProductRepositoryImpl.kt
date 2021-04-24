package com.example.zarbondistributionagent.domain.repositories.impl

import android.util.Log
import com.example.zarbondistributionagent.data.models.productsmodel.AboutProduct
import com.example.zarbondistributionagent.data.source.remote.retrofit.AboutProductApiInterface
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.domain.repositories.repo.AboutProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AboutProductRepositoryImpl : AboutProductRepository {
    private val api = ApiClient.retrofit.create(AboutProductApiInterface::class.java)
    override suspend fun getAboutProduct(id: Int):
            Flow<Result<Pair<Int, AboutProduct?>>> = flow {
        try {
            val response = api.getAboutProduct("product/product-with-presentation/${id}/")
            val code = response.code()
            if (code == 200) {
                emit(Result.success(Pair(200, response.body())))
            } else if (code == 500 || code == 400 || code == 404) {
                emit(Result.success(Pair(404, null)))
            }
        } catch (e: Exception) {
            Log.d("SELL", "exception = $e")
        }
    }
}