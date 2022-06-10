package com.example.cemaraapps

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.cemaraapps.model.DataUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<DataUser> {
        return dataStore.data.map {
            DataUser(
                it[IdToken_KEY] ?: "",
            )
        }
    }

    suspend fun saveUser(user: DataUser) {
        dataStore.edit {
            it[IdToken_KEY] = user.idToken

        }
    }

    suspend fun logout() {
        dataStore.edit {
            it[IdToken_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val IdToken_KEY = stringPreferencesKey("IdToken")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

