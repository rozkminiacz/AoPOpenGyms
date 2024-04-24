package dev.nowoczesny.aop.opengym

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.nowoczesny.aop.opengym.ui.theme.OpenGymsTheme
import timber.log.Timber

@Composable
fun PlaceListElement(element: PlaceListElementDisplayable, clicked: () -> Unit = {}) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            clicked()
        }
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            AsyncImage(
                model = element.imageUrl,
                contentDescription = element.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(0.65f)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = BrushPainter(
                    Brush.linearGradient(
                        listOf(
                            Color.Black,
                            Color.LightGray
                        )
                    )
                ),
                error = BrushPainter(
                    Brush.linearGradient(
                        listOf(
                            Color.LightGray,
                            Color.Black
                        )
                    )
                )
            )
        }
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
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
private fun PlaceListElementPreview() {
    OpenGymsTheme {
        Column {
            PlaceListElement(
                element = PlaceListElementDisplayable(
                    id = "123",
                    name = "boisko do siatkówki plażowej",
                    shortDescription = "zlokalizowane przy basenie Wandzianka w Nowej Hucie w Krakowie",
                    imageUrl = "https://lh5.googleusercontent.com/p/AF1QipPIb4AB9J2pII0elSMIW1E1R-lU7XNlAmcHU_1q=w408-h306-k-no"
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            PlaceListElement(
                element = PlaceListElementDisplayable(
                    id = "123",
                    name = "boisko do siatkówki plażowej",
                    shortDescription = "zlokalizowane przy basenie Wandzianka w Nowej Hucie w Krakowie",
                    imageUrl = "https://lh5.googleusercontent.com/p/AF1QipPIb4AB9J2pII0elSMIW1E1R-lU7XNlAmcHU_1q=w408-h306-k-no"
                )
            )
        }
    }
}