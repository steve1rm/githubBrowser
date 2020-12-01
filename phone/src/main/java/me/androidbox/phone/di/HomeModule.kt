package me.androidbox.phone.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.androidbox.di.viewmodel.ViewModelKey
import me.androidbox.phone.GoogleSignIn
import me.androidbox.phone.GoogleSignInImp
import me.androidbox.phone.viewmodel.HomeViewModel

@Module
interface HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindsViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    fun bindsGoogleSignIn(googleSignInImp: GoogleSignInImp): GoogleSignIn
}
