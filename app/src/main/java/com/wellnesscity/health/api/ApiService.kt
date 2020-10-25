package com.wellnesscity.health.api

import com.wellnesscity.health.models.Diet
import retrofit2.http.GET

interface ApiService {

    @GET // TODO: Endpoints needed
    fun getDiets(): List<Diet>

}