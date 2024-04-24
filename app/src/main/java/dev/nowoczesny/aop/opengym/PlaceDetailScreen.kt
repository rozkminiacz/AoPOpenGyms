package dev.nowoczesny.aop.opengym

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PlaceDetailScreen(viewModel: PlaceDetailViewModel = viewModel()) {

    PlaceListElement(
        element = PlaceListElementDisplayable(
            id = "123",
            name = "boisko do siatkówki plażowej",
            shortDescription = "zlokalizowane przy basenie Wandzianka w Nowej Hucie w Krakowie",
            imageUrl = "https://lh5.googleusercontent.com/p/AF1QipPIb4AB9J2pII0elSMIW1E1R-lU7XNlAmcHU_1q=w408-h306-k-no"
        )
    )
}