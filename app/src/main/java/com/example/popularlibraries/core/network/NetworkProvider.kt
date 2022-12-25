package com.example.popularlibraries.core.network

import com.example.popularlibraries.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkProvider {

    val usersApi: UsersApi by lazy { createRetrofit().create(UsersApi::class.java) }

    private fun createGson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private fun createRetrofit() = Retrofit.Builder()
        .client(createClientWithInterceptor())
        .baseUrl(BuildConfig.SERVER_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(createGson()))
        .build()

    private fun createClientWithInterceptor(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "MY_API_KEY")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return client.build()
    }
}