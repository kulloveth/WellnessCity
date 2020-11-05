package com.wellnesscity.health.ui.healthtips

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wellnesscity.health.data.FirebaseServices
import com.wellnesscity.health.data.model.HealthObject
import com.wellnesscity.health.data.model.HealthTipX
import com.wellnesscity.health.data.model.Resource
import com.wellnesscity.health.util.NetworkControler
import com.wellnesscity.health.util.equalsIgnoreCase
import timber.log.Timber

/**
 * Created by Loveth Nwokike on 25/10/2020
 * */
class HealthTipsViewModel @ViewModelInject constructor(
    private val firebaseServices: FirebaseServices,
    private val networkControler: NetworkControler
) : ViewModel() {

    private val _healthLiveData = MutableLiveData<Resource<List<HealthTipX>>>()

    fun fetchHealthData(): LiveData<Resource<List<HealthTipX>>> {
        if (networkControler.isConnected()) {
            firebaseServices.fetchData().addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result?.let { result ->
                        val i = result.documents[1]?.toObject(HealthObject::class.java)
                        i?.let {
                            _healthLiveData.postValue(Resource.success(it.list))
                            Timber.d("${i.list}")
                        }
                    }
                }
                Timber.d("for ${_healthLiveData.value}")
            }
        } else {
            _healthLiveData.postValue(Resource.error(null, "No internet connection"))
        }
        return _healthLiveData
    }
}