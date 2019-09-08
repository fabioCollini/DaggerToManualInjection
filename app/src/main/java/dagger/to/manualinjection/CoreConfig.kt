package dagger.to.manualinjection

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    @Singleton
    fun providePrefs(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)
}

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    val app: Application
    val prefs: SharedPreferences

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CoreComponent
    }
}
