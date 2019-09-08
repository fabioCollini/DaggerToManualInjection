package dagger.to.manualinjection

interface CityRepo {
    suspend fun getCurrentCity(): String
}