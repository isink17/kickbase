package hr.isinkovic.kickbase.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.isinkovic.kickbase.api.MatchesResponse
import hr.isinkovic.kickbase.data.MatchesRepository
import kotlinx.coroutines.*

class MatchesViewModel(private val repository: MatchesRepository) : ViewModel() {
    private var _matches: MutableLiveData<MatchesResponse> = MutableLiveData()
    val matches: LiveData<MatchesResponse>
        get() = _matches

    fun getMatchesData() {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = repository.getMatches()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _matches.value = response.body()
                } else {
                    Log.d("Error", "Error fetching data")
                }
            }
        }
    }

}