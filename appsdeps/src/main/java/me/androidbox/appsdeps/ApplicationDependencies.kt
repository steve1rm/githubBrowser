package me.androidbox.appsdeps

import android.content.Context
import me.androidbox.domain.repository.AppRepository

interface ApplicationDependencies {
    fun appRepository(): AppRepository
}

fun Context.applicationDependencies(): ApplicationDependencies {
    return (applicationContext as? HasApplicationDependencies)?.getApplicationDependencies()
        ?: throw IllegalStateException("Application must implement HasApplicationDependencies")
}