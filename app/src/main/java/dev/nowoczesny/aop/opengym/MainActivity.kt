package dev.nowoczesny.aop.opengym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.nowoczesny.aop.opengym.ui.theme.OpenGymsTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Preview
@Composable
fun MainContent() {

    OpenGymsTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.fillMaxSize()) {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Routes.list) {
                    composable(route = Routes.list) {
                        PlacesListScreen { placeId ->
                            navController.navigate(Routes.details + "/$placeId")
                        }
                    }
                    composable(route = Routes.detailsSchema) {
                        val id = it.arguments?.getString("id")
                        Timber.d("Id in composable: $id")
                        PlaceDetailScreen()
                    }
                }
            }
        }
    }
}