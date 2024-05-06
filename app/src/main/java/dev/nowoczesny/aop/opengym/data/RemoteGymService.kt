package dev.nowoczesny.aop.opengym.data

import dev.nowoczesny.aop.opengym.domain.GymEntity
import dev.nowoczesny.aop.opengym.domain.GymService

/**
 * Created by jaroslawmichalik on 06/05/2024
 */
class RemoteGymService(private val networkService: NetworkService): GymService {
    override suspend fun getAll(): List<GymEntity> {
        return networkService.getData()
            .map { it.toEntity() }
    }

    override suspend fun getById(id: String): GymEntity {
        return networkService.getById(id).toEntity()
    }
}