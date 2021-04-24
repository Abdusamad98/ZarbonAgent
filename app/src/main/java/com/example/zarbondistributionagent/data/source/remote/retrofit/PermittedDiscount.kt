package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.discountsmodel.PermittedDiscountData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PermittedDiscount {
    @GET
    suspend fun getPermittedDiscount(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<PermittedDiscountData>
}