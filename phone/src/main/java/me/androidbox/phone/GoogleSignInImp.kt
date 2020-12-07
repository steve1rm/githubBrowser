package me.androidbox.phone

import android.app.Application
import javax.inject.Inject

class GoogleSignInImp @Inject constructor(val application: Application): GoogleSignIn {

    override fun signIn() {
        val app = application.applicationContext

        val result = setComponent {
            "this is the string"
        }
    }

    fun setComponent(action: () -> String): Int {
        val result = action()

        return result.length
    }

    fun <T> ViewModelStoreOwnerExt.getComponentExt(createComponent: () -> T): T {
        val viewModelStoreOwnerExt = ViewModelProviderExt(this)

        val result = createComponent()

        return result
    }

    fun inject() {
        val fragmentExt = FragmentExt()
        val result = fragmentExt.getComponentExt {

        }
    }
}

interface GoogleSignIn {
    fun signIn()
}

class FragmentExt : BaseFragmentExt() {
    fun createFragment() {

    }
}

open class BaseFragmentExt : ViewModelStoreOwnerExt {
    override fun getViewModelStoreExt(): String {
        return "This is the string from the store"
    }
}

interface ViewModelStoreOwnerExt {
    fun getViewModelStoreExt(): String
}

class ViewModelProviderExt(val owner: ViewModelStoreOwnerExt)