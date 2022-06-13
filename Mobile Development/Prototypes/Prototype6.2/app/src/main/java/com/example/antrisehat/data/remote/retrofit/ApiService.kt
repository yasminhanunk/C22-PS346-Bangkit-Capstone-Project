package com.example.antrisehat.data.remote.retrofit

import com.example.antrisehat.data.remote.response.ApiResponse
import com.example.antrisehat.data.remote.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") pass: String
    ): Call<ApiResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") pass: String
    ): Call<LoginResponse>

    //@Multipart
    //@POST("stories")
    //fun addStories(
    //    @Header("Authorization") token: String,
    //    @Part("description") des: String,
    //    @Part file: MultipartBody.Part
    //): Call<ApiResponse>

    //@GET("stories")
    //fun getAllStories(
    //    @Header("Authorization") token: String
    //): Call<AllStoriesResponse>
}