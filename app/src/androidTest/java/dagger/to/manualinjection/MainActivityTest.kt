package dagger.to.manualinjection

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java, false, false)

    private val repoMock = mock<CityRepo>()

    private val fakePrefs = FakeSharedPreferences()

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<MyApp>()
        //replace myFeatureComponent with a fake one. It's been already created in the app, in a better implementation
        //the app should create the component only if a fake one is not available
        app.myFeatureComponent = object : MyFeatureComponent {
            override val prefs = fakePrefs
            override val repo = repoMock
        }
    }

    @Test
    fun showCityOnSuccess() = runBlockingTest {
        whenever(repoMock.getCurrentCity()) doReturn FLORENCE

        rule.launchActivity(null)

        onView(withId(R.id.text))
            .check(matches(withText(FLORENCE)))
    }

    @Test
    fun showMessageOnException() = runBlockingTest {
        whenever(repoMock.getCurrentCity()) doThrow RuntimeException("Error!")

        rule.launchActivity(null)

        onView(withId(R.id.text))
            .check(matches(withText("Error retrieving current city: Error!")))
    }

    companion object {
        private const val FLORENCE = "Florence"
    }
}
