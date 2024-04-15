package dev.nowoczesny.aop.opengym

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.nowoczesny.aop.opengym.ui.theme.OpenGymsTheme

data class PlaceListElementDisplayable(
    val name: String,
    val shortDescription: String,
    val imageUrl: String?
)

@Composable
fun PlaceListElement(element: PlaceListElementDisplayable) {
    Row {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = element.name,
                modifier = Modifier
                    .height(200.dp),
            )
        }
        Column {
            Text(
                text = element.name,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = element.shortDescription,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceListElementPreview() {
    OpenGymsTheme {
        PlaceListElement(
            element = PlaceListElementDisplayable(
                name = "boisko do siatkówki plażowej",
                shortDescription = "zlokalizowane przy basenie Wandzianka w Nowej Hucie w Krakowie",
                imageUrl = null
            )
        )
    }

}