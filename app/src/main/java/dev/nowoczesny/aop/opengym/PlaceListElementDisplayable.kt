package dev.nowoczesny.aop.opengym

data class PlaceListElementDisplayable(
    val name: String,
    val shortDescription: String,
    val imageUrl: String?
)

fun Gym.toDisplayable(): PlaceListElementDisplayable {
    return PlaceListElementDisplayable(
        name = name,
        shortDescription = address,
        imageUrl = imageUrl
    )
}