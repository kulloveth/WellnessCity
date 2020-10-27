package com.wellnesscity.health.util

import android.content.Context
import com.google.gson.Gson
import com.wellnesscity.health.data.model.HealthObject
import com.wellnesscity.health.data.model.HealthTipX
import com.wellnesscity.health.data.model.IllnessObject
import org.json.JSONObject
import timber.log.Timber

object JsonUtils {
    fun readIllnessJsonFile(context: Context):IllnessObject {
        val jsonString =
            context.applicationContext.assets.open("illness.json").bufferedReader()
                .use { it.readText() }
        val illnessObject = Gson().fromJson(jsonString, IllnessObject::class.java)
        Timber.d("${illnessObject.conditions}")
       return illnessObject
    }

    fun readHealthJsonFile(context: Context):HealthObject {
        val jsonString =
            context.assets.open("health_tips.json").bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val illnessData = jsonObject.getJSONArray("health_tips")
       val healthList = mutableListOf<HealthTipX>()
        for (i in 0 until illnessData.length()) {
            val illnessArray = illnessData.getJSONObject(i)
            val id = illnessArray.getInt("id")
            val topic = illnessArray.getString("topic")
            val img = illnessArray.getString("icon_url")
            val detail = illnessArray.getString("detail")
            val healthTips = HealthTipX(detail, img, id, topic)
            healthList.add(healthTips)

        }
        Timber.d("$healthList")
        return HealthObject(healthList)
    }
}