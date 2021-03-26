package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.planmodel.PlanData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PlanApiInterface {
    @GET
    suspend fun getPlans(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<List<PlanData>>

}