package dagger.to.manualinjection

import android.content.SharedPreferences
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class MyActivityScope

@Module
class MyActivityModule {
    @Provides
    fun provideViewModel(repo: CityRepo) = MyViewModel(repo)
}

@MyActivityScope
@Component(modules = [MyActivityModule::class], dependencies = [MyFeatureComponent::class])
interface MyActivityComponent {
    val prefs: SharedPreferences
    val myViewModel: MyViewModel
}