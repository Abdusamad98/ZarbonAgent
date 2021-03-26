package com.example.zarbondistributionagent.domain.repositories.impl

import com.example.zarbondistributionagent.data.models.discountsmodel.DiscountedProduct
import com.example.zarbondistributionagent.data.source.remote.retrofit.ApiClient
import com.example.zarbondistributionagent.data.source.remote.retrofit.DiscountedProductsApi
import com.example.zarbondistributionagent.domain.repositories.repo.DiscountedProductsRepository
import com.example.zarbondistributionagent.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class DiscountedProductsRepositoryImpl : DiscountedProductsRepository {
    private val api = ApiClient.retrofit.create(DiscountedProductsApi::class.java)
    override suspend fun getDiscountedProducts(discountId: String): Flow<Result<List<DiscountedProduct>?>> = flow {

        try {
            val response = api.getDiscountedProducts("expense_discount/discount-products/${discountId}/")
            if (response.code() == 200) {
                emit(Result.success(response.body()))
                log(response.body().toString(), "QQQ")
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
            log("TTT", "exception = $e" + "Xatolik!")
        }
    }


}