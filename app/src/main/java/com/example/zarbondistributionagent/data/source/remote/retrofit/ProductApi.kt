package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.getproduct.productFullData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductApi {

    @GET
    suspend fun getProduct(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<productFullData>
}