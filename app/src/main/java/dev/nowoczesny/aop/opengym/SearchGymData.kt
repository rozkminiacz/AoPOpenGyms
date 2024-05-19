package dev.nowoczesny.aop.opengym

import dev.nowoczesny.aop.opengym.domain.GymEntity
import dev.nowoczesny.aop.opengym.domain.GymService

class SearchGymData(private val gymService: GymService) {
    suspend fun execute(searchQuery: String): List<GymEntity>{
        return gymService.search(searchQuery)
    }
}
