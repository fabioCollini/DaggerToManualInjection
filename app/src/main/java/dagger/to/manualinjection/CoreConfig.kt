package dagger.to.manualinjection

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

interface CoreComponent {
    val app: Application
    val prefs: SharedPreferences
}

class CoreComponentImpl(
    override val app: Application
) : CoreComponent {
    override val prefs by lazy {
        PreferenceManager.getDefaultSharedPreferences(app)
    }
}
