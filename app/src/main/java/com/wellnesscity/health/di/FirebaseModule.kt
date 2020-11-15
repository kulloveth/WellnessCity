package com.wellnesscity.health.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.wellnesscity.health.data.FirebaseServices
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
class FirebaseModule {

    @Provides
    @Singleton
    fun provideCloudFirestore() = FirebaseFirestore.getInstance()

}