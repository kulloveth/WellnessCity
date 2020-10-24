package com.wellnesscity.health.util

import android.content.Context
import com.wellnesscity.health.data.ConditionsWithSymptom
import org.json.JSONObject
import timber.log.Timber

object JsonUtils {

    fun readIllnessJsonFile(context: Context) {
        val jsonString =
            context.assets.open("health_tips.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val illnessData = jsonObject.getJSONArray("health_tips")

        for (i in 0 until illnessData.length()) {
            val  illnessArray = illnessData.getJSONObject(i)
            val id = illnessArray.getInt("id")
            val condition = illnessArray.getString("condition")
            val img = illnessArray.getString("img_url")
            val desc = illnessArray.getString("desc")
            val treatment = illnessArray.getString("treatment")
            val symptomsList = mutableListOf<String>()
            val symptomsArray = illnessArray.getJSONArray("symptoms")
            for (i in 0 until symptomsArray.length()){
                val symptoms = symptomsArray.getString(i)
                symptomsList.add(symptoms) }
            val causesList = mutableListOf<String>()
            val causesArray = illnessArray.getJSONArray("causes")
            for (i in 0 until causesArray.length()){
                val causes = causesArray.getString(i)
                causesList.add(causes) }

            val conditionsWithSymptoms = ConditionsWithSymptom(treatment,causesList,condition,desc,id,img,symptomsList)
            Timber.d("$conditionsWithSymptoms")
        }


    }
}