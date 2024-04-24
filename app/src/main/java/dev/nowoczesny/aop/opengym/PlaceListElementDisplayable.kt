package dev.nowoczesny.aop.opengym

data class PlaceListElementDisplayable(
    val id: String,
    val name: String,
    val shortDescription: String,
    val imageUrl: String?
)

fun Gym.toDisplayable(): PlaceListElementDisplayable {
    return PlaceListElementDisplayable(
        name = name,
        shortDescription = address,
        imageUrl = imageUrl,
        id = id.toString()
    )
}