package com.example.android.daggerappllication.api


data class Articles(val articles: List<Article>)

data class Article(
    val source: Sources,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
)

data class Sources(
    val id: String?,
    val name: String
)