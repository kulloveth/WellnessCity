package com.wellnesscity.health.data

import android.content.Context
import com.wellnesscity.health.util.JsonUtils

class FirebaseServices(context: Context) {
    init {
        JsonUtils.readIllnessJsonFile(context)
        JsonUtils.readHealthJsonFile(context)
    }
}