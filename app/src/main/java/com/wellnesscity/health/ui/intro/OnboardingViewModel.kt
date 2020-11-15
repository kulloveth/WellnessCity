package com.wellnesscity.health.ui.intro

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.wellnesscity.health.data.DataStorePreference
import kotlinx.coroutines.launch

/**
 * Created by Loveth Nwokike on 14/11/2020
 * */
class OnboardingViewModel @ViewModelInject constructor(private val preference: DataStorePreference) :
    ViewModel() {


    fun saveOnboarding(save: Boolean) {
        viewModelScope.launch {
            preference.saveOnboarding(save)
        }
    }

    fun fetchOnboarding() = preference.fetchOnboarding().asLiveData()
}