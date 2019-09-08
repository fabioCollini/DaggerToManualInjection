package dagger.to.manualinjection

import android.app.Application

class MyApp : Application() {
    val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    lateinit var myFeatureComponent: MyFeatureComponent

    override fun onCreate() {
        super.onCreate()
        myFeatureComponent = DaggerMyFeatureComponent.builder().coreComponent(coreComponent).build()
    }
}