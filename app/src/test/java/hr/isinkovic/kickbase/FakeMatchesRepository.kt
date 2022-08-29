package hr.isinkovic.kickbase

import hr.isinkovic.kickbase.api.Match

class FakeMatchesRepository {
    private val matches: MutableList<Match> = mutableListOf()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }



    fun getMatches(): List<Match> {
//            emit(matches)
        return matches
    }

}