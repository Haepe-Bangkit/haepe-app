package com.example.cemaraapps

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.cemaraapps.model.DataFamily
import com.example.cemaraapps.model.DataUser
import com.example.cemaraapps.model.UserId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<DataUser> {
        return dataStore.data.map {
            DataUser(
                it[IdToken_KEY] ?: "",
                it[isLogin_KEY] ?: false
            )
        }
    }
    fun getId(): Flow<UserId> {
        return dataStore.data.map {
            UserId(
                it[IdUser_KEY] ?: ""
            )
        }
    }
    suspend fun saveUserId(user: UserId) {
        dataStore.edit {
            it[IdUser_KEY] = user.userId

        }
    }
    suspend fun saveUser(user: DataUser) {
        dataStore.edit {
            it[IdToken_KEY] = user.idToken
            it[isLogin_KEY] = user.isLogin

        }
    }

    suspend fun logout() {
        dataStore.edit {
            it[IdToken_KEY] = ""
            it[isLogin_KEY] = false
            it[IdUser_KEY]  = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val IdToken_KEY = stringPreferencesKey("IdToken")
        private val familyId_KEY = booleanPreferencesKey("familyId")
        private val isLogin_KEY = booleanPreferencesKey("isLogin")
        private val IdUser_KEY = stringPreferencesKey("IdToken")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

