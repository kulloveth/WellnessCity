package com.wellnesscity.health.data

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.wellnesscity.health.data.model.ConditionsWithSymptom
import com.wellnesscity.health.data.model.HealthTipX
import com.wellnesscity.health.data.model.IllnessObject
import com.wellnesscity.health.data.model.Resource
import com.wellnesscity.health.util.JsonUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Loveth Nwokike on 25/10/2020
 * */
class FirebaseServices @Inject constructor(
    private val firestore: FirebaseFirestore,
    @ApplicationContext  private val context: Context
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
        firestore.collection("wellness").document("health-tips").set(conditions).addOnCompleteListener {
            if (it.isSuccessful) {
                Timber.d("completed $conditions")
            } else {
                Timber.d("error occured")
            }

        }
    }


    fun fetchData() = firestore.collection("wellness").get()

}