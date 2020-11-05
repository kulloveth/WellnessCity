package com.wellnesscity.health.data

import com.wellnesscity.health.BuildConfig
import com.wellnesscity.health.data.api.ApiService
import javax.inject.Inject

/**
 * Created by Loveth Nwokike on 28/10/2020
 */
class Repository @Inject constructor(private val service: ApiService) {
    suspend fun fetchDietRecipe(diet: String) = service.getDiets(diet, 40, BuildConfig.API_KEY)
}