package com.wellnesscity.health.ui.diet

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wellnesscity.health.data.DataStorePreference
import com.wellnesscity.health.data.Repository
import com.wellnesscity.health.data.api.ApiService
import com.wellnesscity.health.data.model.DietResponse
import com.wellnesscity.health.data.model.Resource
import com.wellnesscity.health.util.NetworkControler
import kotlinx.coroutines.launch


/**
 * Created by Loveth Nwokike on 28/10/2020
 * */
class DietViewModel @ViewModelInject constructor(
    apiService: ApiService,
    private val repository: Repository = Repository(apiService),
    private val networkControler: NetworkControler,
    private val preference: DataStorePreference
) : ViewModel() {

    private val _recipeLiveData = MutableLiveData<Resource<DietResponse>>()

    fun fetchRecipeData(diet: String): LiveData<Resource<DietResponse>> {
        _recipeLiveData.postValue(Resource.loading(null))
        if (networkControler.isConnected()){
        viewModelScope.launch {
            _recipeLiveData.postValue(Resource.success(repository.fetchDietRecipe(diet)))
        }}else{
            _recipeLiveData.postValue(Resource.error(null,"No internet Connection"))
        }
        return _recipeLiveData
    }

    fun saveDiet(diet:String){
        viewModelScope.launch {
            preference.saveDiet(diet)
        }
    }

    fun getDiet() = preference.fetchDiet().asLiveData()

}