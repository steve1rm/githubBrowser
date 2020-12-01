package me.androidbox.phone

import android.app.Application
import javax.inject.Inject

class GoogleSignInImp @Inject constructor(val application: Application): GoogleSignIn {

    override fun signIn() {
        val app = application.applicationContext
    }
}

interface GoogleSignIn {
    fun signIn()
}
