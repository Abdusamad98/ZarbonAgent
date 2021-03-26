package com.example.zarbondistributionagent.data.source.remote.retrofit
import com.example.zarbondistributionagent.app.App
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://159.65.233.187:8000/api/")
        .client(getHttpClientImage())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @JvmStatic
    private fun getHttpClientImage(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addLogging()
//        httpClient.addInterceptor{ chain->
//            val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${TokenSaver.token}").build()
//            chain.proceed(request)
//        }
        return httpClient.build()
    }
}

fun OkHttpClient.Builder.addLogging(): OkHttpClient.Builder {
    val context = App.instance
    val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            com.example.zarbondistributionagent.utils.log(message)
        }
    })
    logging.level = HttpLoggingInterceptor.Level.HEADERS
    if (false) {
        addNetworkInterceptor(logging)
        addNetworkInterceptor(ChuckInterceptor(context))
    }
    return this
}