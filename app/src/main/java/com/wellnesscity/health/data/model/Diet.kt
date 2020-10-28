package com.wellnesscity.health.data.model


data class DietResponse(val results: List<Diet>)
data class Diet(val id: Int, val title: String, val image: String)