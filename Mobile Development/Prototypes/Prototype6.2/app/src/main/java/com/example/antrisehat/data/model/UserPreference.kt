package com.example.antrisehat.data.model

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>){

    fun getUser(): Flow<UserModel> {
        return dataStore.data.map {
            UserModel(
                it[NAME_KEY] ?: "",
                it[EMAIL_KEY] ?: "",
                it[PASSWORD_KEY] ?: "",
                it[USERID_KEY] ?: "",
                it[TOKEN_KEY] ?: "",
                it[STATE_KEY] ?: false,

                it[NIK_KEY] ?: 0,
                it[TELP_KEY] ?: 0,
                it[DATE_KEY] ?: 0,
                it[JENSKEL_KEY] ?: false
            )
        }
    }

    suspend fun saveUser(user: UserModel) {
        dataStore.edit {
            it[NAME_KEY] = user.name
            it[EMAIL_KEY] = user.email
            it[PASSWORD_KEY] = user.password
            it[USERID_KEY] = user.userId
            it[TOKEN_KEY] = user.token
            it[STATE_KEY] = user.isLogin

            it[NIK_KEY] = user.nik
            it[TELP_KEY] = user.noTelp
            it[DATE_KEY] = user.tglLahir
            it[JENSKEL_KEY] = user.jensKelamin
        }
    }

    suspend fun logout() {
        dataStore.edit {
            it[STATE_KEY] = false
            it[NAME_KEY] = ""
            it[EMAIL_KEY] = ""
            it[USERID_KEY] = ""
            it[TOKEN_KEY] = ""
            it[PASSWORD_KEY] = ""

            it[NIK_KEY] = 0
            it[TELP_KEY] = 0
            it[DATE_KEY] = 0
            it[JENSKEL_KEY] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val USERID_KEY = stringPreferencesKey("userId")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val STATE_KEY = booleanPreferencesKey("state")

        private val NIK_KEY = intPreferencesKey("nik")
        private val TELP_KEY = intPreferencesKey("noTelp")
        private val DATE_KEY = intPreferencesKey("tglLahir")
        private val JENSKEL_KEY = booleanPreferencesKey("jensKel")

        fun getInstance(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}