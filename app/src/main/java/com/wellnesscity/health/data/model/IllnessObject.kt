package com.wellnesscity.health.data.model

import com.google.gson.annotations.SerializedName

data class IllnessObject(
    @SerializedName("conditions_with_symptoms")val conditions: List<ConditionsWithSymptom>
)