package dev.nowoczesny.aop.opengym

data class PlaceListState(
    val gymList: List<PlaceElementDisplayable>,
    val searchHints: List<String> = emptyList(),
    val searchQuery: String = "",
    val loading: Boolean,
    val error: String?
)