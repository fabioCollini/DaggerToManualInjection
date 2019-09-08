package dagger.to.manualinjection

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class MyViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val repo: CityRepo = mock()

    private val viewModel = MyViewModel(repo)

    @Test
    fun loadDataUsingRepo() = runBlockingTest {
        whenever(repo.getCurrentCity()) doReturn FLORENCE

        viewModel.loadData()

        assertThat(viewModel.cityLiveData.value).isEqualTo(FLORENCE)
    }

    companion object {
        private const val FLORENCE = "Florence"
    }
}