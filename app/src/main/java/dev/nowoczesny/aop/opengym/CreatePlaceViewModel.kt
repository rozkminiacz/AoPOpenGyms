package dev.nowoczesny.aop.opengym

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nowoczesny.aop.opengym.domain.GymEntity
import dev.nowoczesny.aop.opengym.domain.GymService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatePlaceViewModel(private val savePlaceUseCase: SavePlaceUseCase) : ViewModel() {
    private val innerState = MutableStateFlow<CreatePlaceState>(CreatePlaceState.EMPTY)
    val state: StateFlow<CreatePlaceState>
        get() = innerState.asStateFlow()


    fun save(place: CreatePlaceState) {
        viewModelScope.launch {

        }
    }

}

class SavePlaceUseCase (private val service: GymService){
    suspend fun execute(entity: GymEntity){
        service.save(entity)
    }
}

data class CreatePlaceState(val name: String, val description: String, val address: String) {
    companion object {
        val EMPTY = CreatePlaceState("", "", "")
    }
}

