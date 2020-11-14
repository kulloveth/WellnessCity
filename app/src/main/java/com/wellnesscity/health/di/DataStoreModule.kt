package com.wellnesscity.health.di

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import com.wellnesscity.health.data.DataStorePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Loveth Nwokike on 25/10/2020
 */

@Module
@InstallIn(ApplicationComponent::class)
class DataStoreModule {
    @Provides
    @Singleton
    fun provideOnBoardPreference(@ApplicationContext context: Context): DataStore<Preferences>{
       return context.createDataStore(
            name = "onboard"
        )
        //com.wellnesscity.health
    }

    @Provides
    fun provideDatastorePreference(preference:DataStore<Preferences>):DataStorePreference{
        return DataStorePreference(preference)
    }
}