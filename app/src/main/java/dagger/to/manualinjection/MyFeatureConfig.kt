package dagger.to.manualinjection

import android.app.Application
import android.content.SharedPreferences
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class MyFeatureScope

@Module
class MyFeatureModule {
    @Provides
    fun provideCityRepo(app: Application): CityRepo = CityRepoImpl(app)
}

@MyFeatureScope
@Component(modules = [MyFeatureModule::class], dependencies = [CoreComponent::class])
interface MyFeatureComponent {
    val prefs: SharedPreferences
    val repo: CityRepo
}