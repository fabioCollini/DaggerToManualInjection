package dagger.to.manualinjection

import android.content.SharedPreferences

interface MyActivityComponent {
    val prefs: SharedPreferences
    val myViewModel: MyViewModel
}

class MyActivityComponentImpl(
    private val myFeatureComponent: MyFeatureComponent
) : MyActivityComponent, MyFeatureComponent by myFeatureComponent {
    override val myViewModel: MyViewModel
        get() = MyViewModel(repo)
}