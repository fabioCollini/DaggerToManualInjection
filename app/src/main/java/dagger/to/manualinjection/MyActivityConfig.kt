package dagger.to.manualinjection

import dagger.Component
import javax.inject.Scope

@Scope
annotation class MyActivityScope

@MyActivityScope
@Component(dependencies = [MyFeatureComponent::class])
interface MyActivityComponent {
    fun inject(activity: MainActivity)
}