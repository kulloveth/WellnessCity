package com.wellnesscity.health.data

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.wellnesscity.health.data.model.ConditionsWithSymptom
import com.wellnesscity.health.data.model.HealthTipX
import com.wellnesscity.health.data.model.IllnessObject
import com.wellnesscity.health.data.model.Resource
import com.wellnesscity.health.util.JsonUtils
import timber.log.Timber
import javax.inject.Inject

class FirebaseServices @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val context: Context
) {
    val illnessList = mutableListOf<ConditionsWithSymptom>()
    init {

    }
    fun insertIllnessData() {
        val conditions = JsonUtils.readIllnessJsonFile(context)
        firestore.collection("wellness").document("conditions").set(conditions)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.d("completed $conditions")
                } else {
                    Timber.d("error occured")
                }
            }
    }

    fun insertHealthData() {
        val conditions = JsonUtils.readHealthJsonFile(context)
        val condition = hashMapOf<String, List<HealthTipX>>()
        condition["health-tips"] = conditions
        firestore.collection("wellness").document().set(condition).addOnCompleteListener {
            if (it.isSuccessful) {
                Timber.d("completed $conditions")
            } else {
                Timber.d("error occured")
            }

        }
    }


    fun fetchIllnessData() = firestore.collection("wellness").get()
}