package dagger.to.manualinjection

import android.app.Application

class MyApp : Application() {
    val coreComponent: CoreComponent by lazy {
        CoreComponentImpl(this)
    }

    lateinit var myFeatureComponent: MyFeatureComponent

    override fun onCreate() {
        super.onCreate()
        myFeatureComponent = MyFeatureComponentImpl(coreComponent)
    }
}