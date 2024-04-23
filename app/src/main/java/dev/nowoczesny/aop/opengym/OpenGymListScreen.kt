package dev.nowoczesny.aop.opengym

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber


@Composable
fun PlacesListScreen(viewModel: PlacesListViewModel = viewModel()) {

    val state = viewModel.stateFlow.collectAsState()

    val placeListState = state.value

    Column() {
        if (placeListState.loading) {
            CircularProgressIndicator()
        }
        if(placeListState.error!=null){
            Text(text = "Error: ${placeListState.error}")
        }
        LazyColumn() {
            items(placeListState.gymList) { gymElement ->
                Button(onClick = { }) {
                    Text(text = "${gymElement.name}")
                }
            }
        }
    }
}

data class PlaceListState(
    val gymList: List<Gym>,
    val loading: Boolean,
    val error: String?
)

class PlacesListViewModel : ViewModel() {

    // jest lista danych
    // czekamy na listę (loading)
    // nie udało się pobrać (błąd)

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
                val data = networkService.getData()

                mutableStateFlow.value = mutableStateFlow.value.copy(
                    gymList = data,
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

