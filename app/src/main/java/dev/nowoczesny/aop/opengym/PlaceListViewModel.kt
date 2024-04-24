package dev.nowoczesny.aop.opengym

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class PlaceListViewModel : ViewModel() {

    fun clicked(element: PlaceListElementDisplayable) {

    }

    private val mutableStateFlow: MutableStateFlow<PlaceListState> = MutableStateFlow(
        PlaceListState(
            gymList = emptyList(),
            loading = true,
            error = null
        )
    )

    val stateFlow: StateFlow<PlaceListState>
        get() = mutableStateFlow.asStateFlow()

    private val networkService: NetworkService = NetworkModule().networkService

    init {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val data: List<Gym> = networkService.getData()

                val gymList: List<PlaceListElementDisplayable> = data.map {
                    it.toDisplayable()
                }

                mutableStateFlow.value = mutableStateFlow.value.copy(
                    gymList = gymList,
                    loading = false
                )
            } catch (exception: HttpException) {
                Timber.e(exception)
                mutableStateFlow.value = mutableStateFlow.value.copy(
                    loading = false,
                    error = exception.code().toString()
                )
            }
        }
    }
}