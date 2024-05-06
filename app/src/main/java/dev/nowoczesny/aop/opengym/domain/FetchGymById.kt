package dev.nowoczesny.aop.opengym.domain

class FetchGymById(private val service: GymService){
    suspend fun execute(id: String): GymEntity {
        return service.getById(id)
    }
}