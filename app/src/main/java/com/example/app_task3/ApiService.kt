package com.example.app_task3

import retrofit2.Call
import retrofit2.http.GET
interface ApiService {

    @GET("posts")
    fun getPosts(): Call<List<post>>
}