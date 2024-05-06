package dev.nowoczesny.aop.opengym.domain

class FetchAllGymData(private val service: GymService){
    suspend fun execute(): List<GymEntity>{
        return service.getAll()
    }
}