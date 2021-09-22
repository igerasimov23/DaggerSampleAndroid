package com.example.android.daggerappllication.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofit {
    var BASE_URL = "https://newsapi.org/"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(newHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val topStoriesApi: ApiInterface = retrofit().create(ApiInterface::class.java)

    private fun newHttpClient(
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        HttpLoggingInterceptor.Level.BODY
                })
            }
            .build()
    }
}