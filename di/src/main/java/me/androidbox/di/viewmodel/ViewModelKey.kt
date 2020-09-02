package me.androidbox.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val classKey: KClass<out ViewModel>)
