package dev.nowoczesny.aop.opengym

data class PlaceListState(
    val gymList: List<PlaceListElementDisplayable>,
    val loading: Boolean,
    val error: String?
)