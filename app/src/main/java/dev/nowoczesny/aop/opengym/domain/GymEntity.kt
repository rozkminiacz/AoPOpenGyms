package dev.nowoczesny.aop.opengym.domain

data class GymEntity(
    val id: String,
    val name: String,
    val address: String,
    val category: String,
    val location: LocationEntity,
    val imageUrl: String
)