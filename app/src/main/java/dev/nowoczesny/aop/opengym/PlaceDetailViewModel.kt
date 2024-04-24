package dev.nowoczesny.aop.opengym

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import timber.log.Timber

class PlaceDetailViewModel(
    stateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val id = stateHandle.get<String>("id")
        Timber.d("Id in view model: $id")
    }
}