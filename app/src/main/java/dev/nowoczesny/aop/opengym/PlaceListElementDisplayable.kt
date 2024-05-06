package dev.nowoczesny.aop.opengym

import dev.nowoczesny.aop.opengym.domain.GymEntity

data class PlaceListElementDisplayable(
    val id: String,
    val name: String,
    val shortDescription: String,
    val imageUrl: String?
)

fun GymEntity.toDisplayable(): PlaceListElementDisplayable {
    return PlaceListElementDisplayable(
        name = name,
        shortDescription = address,
        imageUrl = imageUrl,
        id = id
    )
}