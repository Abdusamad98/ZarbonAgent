package com.example.zarbondistributionagent.data.source.remote.retrofit

import com.example.zarbondistributionagent.data.models.salarymodel.SalaryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SalaryApiInterface {
    @GET
    suspend fun getSalary(
        @Url url: String
        //  @Header("Authorization") token: String
    ): Response<SalaryData>
}