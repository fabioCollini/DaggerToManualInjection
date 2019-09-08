package dagger.to.manualinjection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MyViewModel(
    private val repo: CityRepo
) : ViewModel() {

    val cityLiveData = MutableLiveData<String>()

    fun loadData() {
        viewModelScope.launch {
            cityLiveData.value = "Loading"
            try {
                cityLiveData.value = repo.getCurrentCity()
            } catch (e: Exception) {
                cityLiveData.value = "Error retrieving current city: ${e.message}"
            }
        }
    }
}