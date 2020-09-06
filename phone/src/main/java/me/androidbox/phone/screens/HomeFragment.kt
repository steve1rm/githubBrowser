package me.androidbox.phone.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import me.androidbox.di.viewmodel.AppViewModelFactory
import me.androidbox.phone.adapter.RepositoryAdapter
import me.androidbox.phone.databinding.FragmentHomeBinding
import me.androidbox.phone.di.inject
import me.androidbox.phone.viewmodel.HomeViewModel
import me.androidbox.phone.viewstate.HomeViewState
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private val homeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    private lateinit var bindings: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.listItemRepository.apply {
            adapter = RepositoryAdapter()
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        homeViewModel.viewStateUpdates.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is HomeViewState.HomeViewStateLoading -> {
                    handleLoadingState(binding)
                }
                is HomeViewState.HomeViewStateLoaded -> {
                    handleLoadedState(state, binding)
                }
                is HomeViewState.HomeViewStateError -> {
                    handleErrorState(state, binding)
                }
            }
        })

        return binding.root
    }

    private fun handleErrorState(state: HomeViewState.HomeViewStateError, binding: FragmentHomeBinding) {
        binding.listItemRepository.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = state.message
        binding.progressBar.visibility = View.GONE
    }

    private fun handleLoadedState(state: HomeViewState.HomeViewStateLoaded, binding: FragmentHomeBinding) {
        binding.listItemRepository.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE
        (binding.listItemRepository.adapter as RepositoryAdapter).setRepositoryItems(state.repositoryList)
    }

    private fun handleLoadingState(binding: FragmentHomeBinding) {
        binding.listItemRepository.visibility = View.GONE
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }
}
