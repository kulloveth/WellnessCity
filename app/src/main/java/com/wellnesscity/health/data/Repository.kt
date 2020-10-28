package com.wellnesscity.health.data

import com.wellnesscity.health.BuildConfig
import com.wellnesscity.health.data.api.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val service: ApiService) {
    suspend fun fetchDietRecipe(diet: String) = service.getDiets(diet, 40, BuildConfig.API_KEY)
}