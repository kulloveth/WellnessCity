package com.wellnesscity.health.data

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class DataStorePreference(private val preferences:DataStore<Preferences>) {

    suspend fun saveOnboarding(save:Boolean) {
        preferences.edit {
            it[preferencesKey<Boolean>(ONBOARD_KEY)] = save
        }
    }

     fun fetchOnboarding() = preferences.data.map {
             it[preferencesKey<Boolean>(ONBOARD_KEY)] ?: false  }

    companion object{
       const val ONBOARD_KEY = "onBoard"
    }
}