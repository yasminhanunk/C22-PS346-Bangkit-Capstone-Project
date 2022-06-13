package com.example.antrisehat.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("loginResult")
    val loginResult: LoginResult,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class LoginResult(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("nik")
    val nik: Int,

    @field:SerializedName("noTelp")
    val noTelp: Int,

    @field:SerializedName("tglLahir")
    val tglLahir: Int,

    @field:SerializedName("jensKelamin")
    val jensKelamin: Boolean,
)
