package hr.isinkovic.kickbase.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hr.isinkovic.kickbase.data.MatchesRepository

class MatchesViewModelFactory(private val repository: MatchesRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MatchesViewModel::class.java)) {
            MatchesViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}