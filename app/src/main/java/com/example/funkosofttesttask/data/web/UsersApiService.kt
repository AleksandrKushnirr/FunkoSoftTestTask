package com.example.funkosofttesttask.data.web

import com.example.funkosofttesttask.data.web.pojo.InfoJson
import com.example.funkosofttesttask.data.web.pojo.UsersJson
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApiService {
    @GET("/api/users?page=2")
    suspend fun getUsersJson(): Response<UsersJson>

    @GET("/api/users/{id}")
    suspend fun getUserInfo(@Path("id") int: Int): Response<InfoJson>
}