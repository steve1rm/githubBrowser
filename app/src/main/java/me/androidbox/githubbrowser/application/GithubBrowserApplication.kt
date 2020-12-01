package me.androidbox.githubbrowser.application

import android.app.Application
import me.androidbox.appsdeps.ApplicationDependencies
import me.androidbox.appsdeps.HasApplicationDependencies

class GithubBrowserApplication : Application(), HasApplicationDependencies {

    private val applicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this,this)
    }

    override fun getApplicationDependencies(): ApplicationDependencies {
        return applicationComponent
    }
}
