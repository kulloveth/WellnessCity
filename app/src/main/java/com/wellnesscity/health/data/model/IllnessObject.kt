package com.wellnesscity.health.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class IllnessObject(
    @SerializedName("conditions_with_symptoms")val conditions: MutableList<ConditionsWithSymptom> = mutableListOf()
)