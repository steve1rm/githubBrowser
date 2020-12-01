package me.androidbox.phone.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import me.androidbox.di.viewmodel.AppViewModelFactory
import me.androidbox.phone.GoogleSignInImp
import me.androidbox.phone.databinding.FragmentHomeBinding
import me.androidbox.phone.di.inject
import me.androidbox.phone.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    @Inject
    lateinit var googleSignInImp: GoogleSignInImp

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    private lateinit var bindings: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        googleSignInImp.signIn()
        return FragmentHomeBinding.inflate(inflater, container, false).root
    }
}
