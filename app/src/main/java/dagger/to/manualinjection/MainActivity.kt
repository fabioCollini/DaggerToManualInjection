package dagger.to.manualinjection

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.observe
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<MyViewModel>

    @Inject
    lateinit var prefs: SharedPreferences

    private val viewModel by viewModel { viewModelProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myApp = application as MyApp

        DaggerMyActivityComponent.builder()
            .myFeatureComponent(myApp.myFeatureComponent)
            .build()
            .inject(this)

        viewModel.loadData()

        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        viewModel.cityLiveData.observe(this) {
            textView.text = it
        }
        if (!prefs.contains(WELCOME_KEY)) {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_LONG).show()
            prefs.edit { putBoolean(WELCOME_KEY, true) }
        }
    }

    companion object {
        private const val WELCOME_KEY = "welcome"
    }
}
