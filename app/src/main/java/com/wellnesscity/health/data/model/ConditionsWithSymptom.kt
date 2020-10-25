package com.wellnesscity.health.data.model

import com.google.gson.annotations.SerializedName


data class ConditionsWithSymptom(
     @SerializedName("treatment")val treatment: String = "",
     @SerializedName("causes")val causes: List<String> = listOf(),
     @SerializedName("condition")val condition: String="",
     @SerializedName("desc")val desc: String="",
     @SerializedName("id")val id: Int=0,
     @SerializedName("img_url")val img_url: String="",
     @SerializedName("symptoms")val symptoms: List<String> = listOf()
)