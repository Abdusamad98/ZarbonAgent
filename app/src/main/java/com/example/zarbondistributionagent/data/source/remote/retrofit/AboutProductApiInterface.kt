package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.productsmodel.AboutProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface AboutProductApiInterface {
    @GET
    suspend fun getAboutProduct(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<AboutProduct>

}