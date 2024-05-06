package dev.nowoczesny.aop.opengym

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber


@Composable
fun PlacesListScreen(viewModel: PlaceListViewModel = koinViewModel(), navigateToDetailScreen: (String)->Unit) {
    val state = viewModel.stateFlow.collectAsState()

    val placeListState = state.value

    PlacesListScreenContent(placeListState = placeListState){
        navigateToDetailScreen(it.id)
    }
}

@Composable
private fun PlacesListScreenContent(
    placeListState: PlaceListState,
    clicked: (PlaceListElementDisplayable) -> Unit
) {
    Column() {
        if (placeListState.loading) {
            CircularProgressIndicator()
        }
        if (placeListState.error != null) {
            Text(text = "Error: ${placeListState.error}")
        }
        LazyColumn() {
            items(placeListState.gymList) { gymElement ->
                PlaceListElement(element = gymElement, clicked = {
                    clicked(gymElement)
                    Timber.d("Clicked place list element: $gymElement")
                })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PlacesListScreenPreview() {

    val element = PlaceListElementDisplayable(
        id = "123",
        name = "boisko do siatkówki plażowej",
        shortDescription = "zlokalizowane przy basenie Wandzianka w Nowej Hucie w Krakowie",
        imageUrl =
        "https://lh5.googleusercontent.com/p/AF1QipPIb4AB9J2pII0elSMIW1E1R-lU7XNlAmcHU_1q=w408-h306-k-no"
    )

    PlacesListScreenContent(
        placeListState = PlaceListState(
            gymList = listOf(element, element, element),
            loading = false,
            error = "wystąpił błąd"
        ),
        clicked = {}
    )
}