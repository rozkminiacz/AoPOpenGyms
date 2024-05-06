package dev.nowoczesny.aop.opengym.domain

interface GymService {
    suspend fun getAll(): List<GymEntity>
    suspend fun getById(id: String): GymEntity
}