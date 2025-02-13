package com.example.boka.data.network.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://rseeshop-api.onrender.com/api/"
    var token : String = "Init token"
        set(value)  {
            field = value
            retrofit = null
        }

    private var retrofit: Retrofit? = null
    val authApi: AuthApi by lazy {
        if (retrofit == null) {
            buildRetrofit()
        }
        retrofit!!.create(AuthApi::class.java)
    }
    val bookApi : BookApi by lazy {
        if (retrofit == null) {
            buildRetrofit()
        }
        retrofit!!.create(BookApi::class.java)
    }
    val historyApi : HistoryApi by lazy {
        if (retrofit == null) {
            buildRetrofit()
        }
        retrofit!!.create(HistoryApi::class.java)
    }

    val masterApi : MasterApi by lazy {
        if (retrofit == null) {
            buildRetrofit()
        }
        retrofit!!.create(MasterApi::class.java)
    }

    val userApi : UserApi by lazy {
        if (retrofit == null) {
            buildRetrofit()
        }
        retrofit!!.create(UserApi::class.java)
    }

    private val tokenInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }

    private fun buildRetrofit() {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(tokenInterceptor)
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}