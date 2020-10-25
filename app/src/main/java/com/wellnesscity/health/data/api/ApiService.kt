package com.wellnesscity.health.data.api

import com.wellnesscity.health.data.models.Diet
import retrofit2.http.GET

interface ApiService {

    @GET // TODO: Endpoints needed
    fun getDiets(): List<Diet>

}