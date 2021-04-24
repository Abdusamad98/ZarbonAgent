package com.example.zarbondistributionagent.data.source.remote.retrofit

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface FreeProductApi {
    @FormUrlEncoded
    @POST("expense_discount/free-product-discount/")
    suspend fun sellFreeProduct(
        @Header("Accept") app_json: String,
        @Field("quantity") price: String,
        @Field("agent") agent: Int,
        @Field("product") product: Int,
        @Field("sell_orders") sell_orders: ArrayList<Int>
    ): Response<Any>
}