package com.example.zarbondistributionagent.ui.viewmodels

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zarbondistributionagent.domain.usecases.AddClientUseCase
import com.example.zarbondistributionagent.domain.usecases.impl.AddClientUseCaseImpl
import com.google.android.gms.maps.GoogleMap

class MapViewModel : ViewModel() {
    private val useCase: AddClientUseCase = AddClientUseCaseImpl.getUseCase()
    val fineUserLocationLiveData= MutableLiveData<Location>()
    val permissionLiveData= MutableLiveData<GoogleMap>()
    val showDialogLiveData= MutableLiveData<Unit>()
    val closeScreenLiveData= MutableLiveData<Unit>()

    fun fineUserLocation(lr: Location) {
        fineUserLocationLiveData.value = lr
    }

    fun gerPermission(map: GoogleMap) {
        permissionLiveData.value = map
    }

    fun showDialogApply() {
        showDialogLiveData.value = Unit
    }

    fun send(location: String) {
        useCase.locationSt = location
        Log.d("TTT","location= $location")
        closeScreenLiveData.value = Unit
    }

    fun closeScreen() {
        closeScreenLiveData.value = Unit
    }

}