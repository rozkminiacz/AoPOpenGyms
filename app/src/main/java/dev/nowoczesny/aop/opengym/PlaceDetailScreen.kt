package dev.nowoczesny.aop.opengym

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaceDetailScreen(viewModel: PlaceDetailViewModel = koinViewModel()) {

    val state = viewModel.stateFlow.collectAsState()

    val placeListState = state.value

    if (placeListState.gym != null) {
        PlaceListElement(
            element = placeListState.gym
        )
    }
    if(placeListState.loading){

    }
    if(placeListState.error!=null){

    }
}
