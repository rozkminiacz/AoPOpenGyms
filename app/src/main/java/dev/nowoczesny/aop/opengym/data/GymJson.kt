package dev.nowoczesny.aop.opengym.data

import dev.nowoczesny.aop.opengym.domain.GymEntity
import dev.nowoczesny.aop.opengym.domain.LocationEntity
import kotlinx.serialization.Serializable


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

fun Gym.toEntity(): GymEntity = GymEntity(
    id = this.id.toString(),
    name = name,
    address = address,
    category = category,
    location = location.toEntity(),
    imageUrl = imageUrl
)

private fun Location.toEntity(): LocationEntity {
    return LocationEntity(latitude, longitude)
}
