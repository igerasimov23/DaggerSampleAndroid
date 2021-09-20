package com.example.android.daggerappllication.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NewsInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val newUrl: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("apiKey", "1734b738c9f547d280d03e13abf208e9")
            .addQueryParameter("q", "tesla")
            .addQueryParameter("from", "2021-08-02")
            .addQueryParameter("sortBy", "publishedAt")
            .build()
        val newRequest: Request = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}