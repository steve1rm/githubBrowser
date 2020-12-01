package me.androidbox.phone.di

import dagger.Component
import me.androidbox.appsdeps.ApplicationDependencies
import me.androidbox.appsdeps.applicationDependencies
import me.androidbox.phone.screens.HomeFragment
import javax.inject.Singleton

@Component(
    dependencies = [ApplicationDependencies::class],
    modules = [HomeModule::class])
interface HomeComponent {

    fun inject(homeFragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(applicationDependencies: ApplicationDependencies): HomeComponent
    }
}

fun HomeFragment.inject() {
    DaggerHomeComponent.factory()
        .create(requireContext().applicationDependencies())
        .inject(this)
}
