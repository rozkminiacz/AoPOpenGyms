package dev.nowoczesny.aop.opengym

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nowoczesny.aop.opengym.data.NetworkService
import dev.nowoczesny.aop.opengym.domain.FetchGymById
import dev.nowoczesny.aop.opengym.domain.GymEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

data class PlaceDetailsState(
    val gym: PlaceListElementDisplayable?,
    val loading: Boolean,
    val error: String?
)

class PlaceDetailViewModel(
    private val fetchGymById: FetchGymById,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val id by lazy {
        stateHandle.get<String>("id")
    }

    private val mutableStateFlow: MutableStateFlow<PlaceDetailsState> = MutableStateFlow(
        PlaceDetailsState(
            gym = null,
            loading = true,
            error = null
        )
    )

    val stateFlow: StateFlow<PlaceDetailsState>
        get() = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val data: GymEntity = fetchGymById.execute(id ?: "")

                mutableStateFlow.value = mutableStateFlow.value.copy(
                    gym = data.toDisplayable(),
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