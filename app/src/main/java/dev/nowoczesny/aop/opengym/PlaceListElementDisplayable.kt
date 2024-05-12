package dev.nowoczesny.aop.opengym

import com.google.android.gms.maps.model.LatLng
import dev.nowoczesny.aop.opengym.domain.GymEntity
import dev.nowoczesny.aop.opengym.domain.LocationEntity

data class PlaceListElementDisplayable(
    val id: String,
    val name: String,
    val shortDescription: String,
    val imageUrl: String?,
    val location: LocationEntity,
) {
    fun toLatLng(): LatLng {
        return LatLng(location.latitude, location.longitude)
    }
}

fun GymEntity.toDisplayable(): PlaceListElementDisplayable {
    return PlaceListElementDisplayable(
        name = name,
        shortDescription = address,
        imageUrl = imageUrl,
        id = id,
        location = location
    )
}