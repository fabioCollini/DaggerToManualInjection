package dagger.to.manualinjection

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.observe

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        MyActivityComponentImpl((application as MyApp).myFeatureComponent)
    }

    private val prefs by lazy { component.prefs }

    private val viewModel by viewModel { component.myViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
