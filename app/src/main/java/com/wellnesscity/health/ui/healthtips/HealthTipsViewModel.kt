package com.wellnesscity.health.ui.healthtips

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wellnesscity.health.data.FirebaseServices
import com.wellnesscity.health.data.model.*
import timber.log.Timber

class HealthTipsViewModel @ViewModelInject constructor(private val firebaseServices: FirebaseServices) :ViewModel(){

    private val _healthLiveData = MutableLiveData<Resource<List<HealthTipX>>>()

    fun fetchHealthData():LiveData<Resource<List<HealthTipX>>>{
        firebaseServices.fetchData().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result?.let { result ->
                    val i = result.documents[2]?.toObject(HealthObject::class.java)
                    i?.let {
                    _healthLiveData.postValue(Resource.success(it.list))
                        Timber.d("${i.list}")
                    }}}
            Timber.d("for ${_healthLiveData.value}")
        }
        return _healthLiveData
    }
}