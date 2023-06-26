package com.example.boka.data.data_source.network.book

import com.example.boka.data.model.Book
import com.example.boka.data.model.NetworkResult
import retrofit2.http.GET

interface BookService {
    @GET("books/top_rated")
    suspend fun getTopRatedBooks() : NetworkResult<List<Book>>
}