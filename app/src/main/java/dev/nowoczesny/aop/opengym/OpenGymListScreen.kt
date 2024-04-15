package dev.nowoczesny.aop.opengym

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


@Composable
fun PlacesListScreen(viewModel: PlacesListViewModel = viewModel()) {
    LazyColumn() {
        items(12) { number ->
            Button(onClick = { }) {
                Text(text = "Kliknij mnie $number")
            }
        }
    }
}

class PlacesListViewModel : ViewModel() {

    private val networkService: NetworkService = NetworkModule().networkService

    init {
        viewModelScope.launch(Dispatchers.IO) {
            networkService.getData().forEach {
                Timber.d("Item: $it")
            }
        }
    }
}

