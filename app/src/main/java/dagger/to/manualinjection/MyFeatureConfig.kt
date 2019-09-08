package dagger.to.manualinjection

import android.content.SharedPreferences

interface MyFeatureComponent {
    val prefs: SharedPreferences
    val repo: CityRepo
}

class MyFeatureComponentImpl(
    private val coreComponent: CoreComponent
) : MyFeatureComponent, CoreComponent by coreComponent {
    override val repo by lazy {
        CityRepoImpl(app)
    }
}