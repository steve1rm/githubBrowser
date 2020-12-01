package me.androidbox.appsdeps

import android.app.Application
import android.content.Context
import me.androidbox.domain.repository.AppRepositoryImp

interface ApplicationDependencies {
    fun appRepository(): AppRepositoryImp
    fun application(): Application
}

fun Context.applicationDependencies(): ApplicationDependencies {
    return (applicationContext as? HasApplicationDependencies)?.getApplicationDependencies()
        ?: throw IllegalStateException("Application must implement HasApplicationDependencies")
}
