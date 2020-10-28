package com.wellnesscity.health.data.api

import com.wellnesscity.health.data.model.DietResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("recipes/complexSearch")
    suspend fun getDiets(
        @Query("diet") diet: String,
        @Query("number") number: Int,
        @Query("apiKey") apiKey: String
    ): DietResponse

}