package com.example.antrisehat.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class UserModel(
    val name: String,
    val email: String,
    val password: String,
    val userId: String,
    val token: String,
    val isLogin: Boolean,

    val nik: Int,
    val noTelp: Int,
    val tglLahir: Int,
    val jensKelamin : Boolean
): Parcelable