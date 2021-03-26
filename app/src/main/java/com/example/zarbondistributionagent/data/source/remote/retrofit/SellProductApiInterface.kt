package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import retrofit2.Response
import retrofit2.http.*

interface SellProductApiInterface {
    @FormUrlEncoded
    @POST("order/sell-order-list/")
    suspend fun sellProduct(
        @Header("Accept") app_json: String,
        @Field("price") price:String,
        @Field("quantity") quantity:String,
        @Field("client") client:Int,
        @Field("product") product:Int
    ) : Response<SellProductResponse>
}

