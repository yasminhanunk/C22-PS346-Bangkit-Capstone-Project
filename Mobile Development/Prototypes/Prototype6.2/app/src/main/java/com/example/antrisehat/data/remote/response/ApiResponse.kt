package com.example.antrisehat.data.remote.response

import com.google.gson.annotations.SerializedName

class ApiResponse (

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String

    )

