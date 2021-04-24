package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.sellmodel.SellProductResponse
import retrofit2.Response
import retrofit2.http.*

interface SellProductApiInterface {
    @FormUrlEncoded
    @POST("order/sell-order-list/")
    suspend fun sellProduct(
        @Header("Accept") app_json: String,
        @Field("order_method") order_method:String,
        @Field("quantity") quantity:String,
        @Field("client") client:Int,
        @Field("product") product:Int,
        @Field("discount") discount:Int,
        @Field("price") price:Int,
    ) : Response<SellProductResponse>
}

