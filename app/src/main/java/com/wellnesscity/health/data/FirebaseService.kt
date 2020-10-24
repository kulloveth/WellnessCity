package com.wellnesscity.health.data

import android.content.Context
import androidx.annotation.RawRes
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.wellnesscity.health.R
import com.wellnesscity.health.data.model.ConditionsWithSymptom
import okio.Buffer
import org.json.JSONObject
import timber.log.Timber
import javax.annotation.Resource

class FirebaseService(private val context: Context) {

    init {
        readJsonFile(R.raw.illness, "conditions_with_symptoms")
    }

    fun readJsonFile(@RawRes resource: Int, fileName: String) {
        val jsonString =
            context.resources.openRawResource(resource).bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val illnessData = jsonObject.getJSONArray(fileName)

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

    companion object {
        val NAMES = JsonReader.Options.of("id", "condition", "desc", "treatment", "img_url")
    }

    val type = Types.newParameterizedType(List::class.java, String::class.java)

    val moshi = Moshi.Builder()
        .build()
    val adapter = moshi.adapter<List<String>>(type)
    fun parse(reader: JsonReader): List<ConditionsWithSymptom> {
        val result = mutableListOf<ConditionsWithSymptom>()

        reader.beginArray()
        while (reader.hasNext()) {
            var id: Int = 0
            var condition: String = ""
            var desc: String = ""
            var treatment = ""
            var img_url = ""

            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.selectName(NAMES)) {
                    0 -> id = reader.nextInt()
                    1 -> condition = reader.nextString()
                    2 -> desc = reader.nextString()
                    3 -> treatment = reader.nextString()
                    4 -> img_url = reader.nextString()
                    else -> {
                        reader.skipName()
                        reader.skipValue()
                    }
                }
            }
            reader.endObject()

            if (id == 0 || condition == "") {
                throw JsonDataException("Missing required field")
            }
            val person = ConditionsWithSymptom(
                id = id,
                condition = condition,
                desc = desc,
                treatment = treatment,
                img_url = img_url
            )
            result.add(person)
        }
        reader.endArray()

        return result
    }

    init {
        val jsonString =
            context.resources.openRawResource(R.raw.illness).bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(jsonString)
        val illnessData = jsonObject.getJSONArray("conditions_with_symptoms")
        val reader = JsonReader.of(Buffer().writeUtf8(illnessData.toString()))
        Timber.d("${parse(reader)} known")
    }

}