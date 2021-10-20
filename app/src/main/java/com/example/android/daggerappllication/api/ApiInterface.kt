package com.example.android.daggerappllication.api

import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET("v2/everything?q=tesla&from=2021-10-02&sortBy=publishedAt&apiKey=1734b738c9f547d280d03e13abf208e9")
    suspend fun getAllData(): Response<Articles>

}