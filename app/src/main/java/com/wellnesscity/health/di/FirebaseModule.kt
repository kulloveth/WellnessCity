package com.wellnesscity.health.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideCloudFirestore() = FirebaseFirestore.getInstance()
}