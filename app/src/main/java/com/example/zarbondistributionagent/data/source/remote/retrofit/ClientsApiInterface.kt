package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.clientmodel.ClientsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ClientsApiInterface {
    @GET
    suspend fun getClientList(
        @Url url: String,
        //  @Header("Authorization") token: String
    ): Response<ClientsData>
}