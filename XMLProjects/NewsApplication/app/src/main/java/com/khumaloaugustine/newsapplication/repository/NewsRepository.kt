package com.khumaloaugustine.newsapplication.repository

import com.khumaloaugustine.newsapplication.api.RetrofitInstance
import com.khumaloaugustine.newsapplication.database.ArticleDatabase
import com.khumaloaugustine.newsapplication.models.Article
import com.khumaloaugustine.newsapplication.models.NewsResponse
import retrofit2.Response

class NewsRepository(private val database: ArticleDatabase) {
    suspend fun getHeadlines(countryCode: String?, pageNumber: Int): Response<NewsResponse?> =
        RetrofitInstance.api.getHeadLines(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse?> =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = database.getArticleDao().upsert(article)

    fun getFavouriteNews() = database.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = database.getArticleDao().deleteArticle(article)
}