package dagger.to.manualinjection

import android.content.SharedPreferences
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
annotation class MyFeatureScope

@Module
abstract class MyFeatureModule {
    @Binds
    abstract fun bindCityRepo(impl: CityRepoImpl): CityRepo
}

@MyFeatureScope
@Component(modules = [MyFeatureModule::class], dependencies = [CoreComponent::class])
interface MyFeatureComponent {
    val prefs: SharedPreferences
    val repo: CityRepo
}