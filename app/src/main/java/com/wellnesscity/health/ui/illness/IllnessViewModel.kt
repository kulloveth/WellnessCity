package com.wellnesscity.health.ui.illness

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wellnesscity.health.data.FirebaseServices
import com.wellnesscity.health.data.model.ConditionsWithSymptom
import com.wellnesscity.health.data.model.IllnessObject
import com.wellnesscity.health.data.model.Resource
import com.wellnesscity.health.util.NetworkControler
import timber.log.Timber

class IllnessViewModel @ViewModelInject constructor(private val firebaseServices: FirebaseServices,private val networkControler: NetworkControler) :ViewModel(){

    private val _illnessLiveData = MutableLiveData<Resource<List<ConditionsWithSymptom>>>()

    fun fetchIllnessData():LiveData<Resource<List<ConditionsWithSymptom>>>{
        if (networkControler.isConnected()){
        firebaseServices.fetchData().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.let { result ->
                    val i = result.documents[0]?.toObject(IllnessObject::class.java)
                    i?.let {
                    _illnessLiveData.postValue(Resource.success(it.conditions))
                        Timber.d("${i.conditions}")
                    }}}
            Timber.d("for ${_illnessLiveData.value}")
        }}else{
            _illnessLiveData.postValue(Resource.error(null,"No internet connection"))
        }
        return _illnessLiveData
    }
}