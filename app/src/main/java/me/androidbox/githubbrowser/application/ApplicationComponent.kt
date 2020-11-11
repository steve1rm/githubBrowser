package me.androidbox.githubbrowser.application

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.androidbox.appsdeps.ApplicationDependencies
import me.androidbox.data.di.GithubApiModule
import me.androidbox.domain.repository.AppRepository
import me.androidbox.domain.repository.AppRepositoryImp
import javax.inject.Singleton

@Singleton
@Component(modules = [GithubApiModule::class])
interface ApplicationComponent : ApplicationDependencies {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}
