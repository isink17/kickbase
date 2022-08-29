package hr.isinkovic.kickbase.data

import hr.isinkovic.kickbase.api.MatchesService

open class MatchesRepository(private val service: MatchesService) {
    suspend fun getMatches() = service.getMatches()
}