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

@Module
@InstallIn(ApplicationComponent::class)
class ViewModelModule {

    @Provides
    @Singleton
    fun provideFireBaseServices(firestore: FirebaseFirestore, @ApplicationContext context: Context):FirebaseServices{
        return FirebaseServices(firestore,context)
    }
}
