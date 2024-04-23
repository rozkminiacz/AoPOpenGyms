package dev.nowoczesny.aop.opengym

import kotlinx.serialization.Serializable
import retrofit2.http.GET


interface NetworkService {
    @GET("/")
    suspend fun getData(): List<Gym>
}


@Serializable
data class Gym(
    val id: Int,
    val name: String,
    val address: String,
    val category: String,
    val location: Location,
    val imageUrl: String
)

@Serializable
data class Location(
    val latitude: Double,
    val longitude: Double
)