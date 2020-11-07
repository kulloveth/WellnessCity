package com.wellnesscity.health.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataStoreModule {
    @Provides
    @Singleton
    fun provideOnBoardPreference(@ApplicationContext context: Context): DataStore<Preferences>{
       return context.createDataStore(
            name = "com.wellnesscity.health.di"
        )
    }
}