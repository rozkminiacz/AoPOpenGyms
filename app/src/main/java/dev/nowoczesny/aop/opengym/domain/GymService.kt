package dev.nowoczesny.aop.opengym.domain

interface GymService {
    suspend fun getAll(): List<GymEntity>
    suspend fun getById(id: String): GymEntity
    suspend fun search(searchQuery: String): List<GymEntity>
    suspend fun save(entity: GymEntity)
}