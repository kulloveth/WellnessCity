package com.wellnesscity.health.ui.illness

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wellnesscity.health.data.FirebaseServices
import com.wellnesscity.health.data.model.ConditionsWithSymptom
import com.wellnesscity.health.data.model.IllnessObject
import com.wellnesscity.health.data.model.Resource
import timber.log.Timber

class IllnessViewModel @ViewModelInject constructor(private val firebaseServices: FirebaseServices) :ViewModel(){

    private val _illnessLiveData = MutableLiveData<Resource<List<ConditionsWithSymptom>>>()

    fun fetchIllnessData():LiveData<Resource<List<ConditionsWithSymptom>>>{
        firebaseServices.fetchIllnessData().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.let { result ->
                    val i = result.documents[1]?.toObject(IllnessObject::class.java)
                    i?.let {
                    _illnessLiveData.postValue(Resource.success(it.conditions))
                        Timber.d("${i.conditions}")
                    }}}
            Timber.d("for ${_illnessLiveData.value}")
        }
        return _illnessLiveData
    }
}