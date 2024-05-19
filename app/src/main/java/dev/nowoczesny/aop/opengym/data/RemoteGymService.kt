package dev.nowoczesny.aop.opengym.data

import dev.nowoczesny.aop.opengym.domain.GymEntity
import dev.nowoczesny.aop.opengym.domain.GymService
import dev.nowoczesny.aop.opengym.domain.LocationEntity

/**
 * Created by jaroslawmichalik on 06/05/2024
 */
class RemoteGymService(
    private val networkService: NetworkService,
    private val gymDao: GymDao
) :
    GymService {
    override suspend fun getAll(): List<GymEntity> {
        return networkService.getData()
            .map { it.toEntity() }
            .also {
                gymDao.insertAll(it.map { it.toDbEntity() })
            }
    }

    override suspend fun getById(id: String): GymEntity {
//        return networkService.getById(id).toEntity()
        return gymDao.findById(id).toEntity()
    }

    override suspend fun search(searchQuery: String): List<GymEntity> {
        return gymDao.searchByQuery(searchQuery).map { it.toEntity() }
    }
}

private fun GymDbEntity.toEntity(): GymEntity {
    return GymEntity(
        id = this.id,
        name = name,
        address = address,
        category = category,
        location = LocationEntity(location.latitude, location.longitude),
        imageUrl = imageUrl
    )
}

private fun GymEntity.toDbEntity(): GymDbEntity {
    return GymDbEntity(
        id = this.id,
        name = name,
        address = address,
        category = category,
        location = LocationDbEntity(location.latitude, location.longitude),
        imageUrl = imageUrl
    )
}
