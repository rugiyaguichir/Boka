package com.example.boka.data.data_source.network.auth.result

import com.example.boka.data.model.User
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UserJson(
    val id : Int,
    val email : String,
    val name : String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("favorite_genres")
    val favoriteGenres : List<Int> = emptyList(),
){
    fun toUser() : User {
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        return User(
            email = email,
            name = name,
            createdAt = LocalDateTime.parse(createdAt, formatter),
            updatedAt = LocalDateTime.parse(updatedAt, formatter),
            favoriteGenres = favoriteGenres,
        )
    }
}