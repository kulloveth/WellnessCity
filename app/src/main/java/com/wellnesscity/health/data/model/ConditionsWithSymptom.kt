package com.wellnesscity.health.data.model

data class ConditionsWithSymptom(
    val treatment: String = "",
    val causes: List<String> = listOf(),
    val condition: String="",
    val desc: String="",
    val id: Int=0,
    val img_url: String="",
    val symptoms: List<String> = listOf()
)