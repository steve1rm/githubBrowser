package me.androidbox.phone.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.di.component.getComponent
import me.androidbox.di.viewmodel.AppViewModelFactory
import me.androidbox.phone.GoogleSignInImp
import me.androidbox.phone.adapter.RepositoryAdapter
import me.androidbox.phone.databinding.FragmentHomeBinding
import me.androidbox.phone.di.inject
import me.androidbox.phone.model.RepoItem
import me.androidbox.phone.viewmodel.HomeViewModel
import me.androidbox.phone.viewstate.HomeViewState
import me.androidbox.phone.viewstate.HomeViewState.*
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
        
        googleSignInImp.inject()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bindings = FragmentHomeBinding.inflate(inflater, container, false)

        bindings.listItemRepository.let {
            it.adapter = RepositoryAdapter()
            it.layoutManager = LinearLayoutManager(context)
            it.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        homeViewModel.viewStateUpdates.observe(viewLifecycleOwner, { homeViewState ->
            when(homeViewState) {
                is HomeViewStateLoading -> {
                    handleLoadingState(bindings)
                }

                is HomeViewStateLoaded -> {
                    handleLoadedState(bindings, homeViewState.repositoryList)
                }

                is HomeViewStateError -> {
                    handleLoadingError(bindings, homeViewState.message)
                }
            }
        })

        return bindings.root
    }

    private fun handleLoadingState(bindings: FragmentHomeBinding) {
        bindings.listItemRepository.isVisible = false
        bindings.progressBar.isVisible = true
        bindings.tvError.isVisible = false
    }

    private fun handleLoadedState(bindings: FragmentHomeBinding, repositoryList: List<RepoItem>) {
        bindings.listItemRepository.isVisible = true
        bindings.progressBar.isVisible = false
        bindings.tvError.isVisible = false

        (bindings.listItemRepository.adapter as RepositoryAdapter)
            .setRepositoryItems(repositoryList)
    }

    private fun handleLoadingError(bindings: FragmentHomeBinding, message: String) {
        bindings.listItemRepository.isVisible = false
        bindings.progressBar.isVisible = true
        bindings.tvError.isVisible = true
        bindings.tvError.text = message
    }

}
