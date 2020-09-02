package me.androidbox.phone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.androidbox.domain.repository.AppRepository
import me.androidbox.phone.model.RepoItem
import me.androidbox.phone.viewstate.HomeViewState
import me.androidbox.phone.viewstate.HomeViewState.HomeViewStateLoading
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _viewState = MutableLiveData<HomeViewState>(HomeViewStateLoading)
    val viewStateUpdates: LiveData<HomeViewState>
        get() = _viewState

    init {
        val topRepos = appRepository.getTopRepos()

        _viewState.value = HomeViewState.HomeViewStateLoaded(
            repositoryList = topRepos.map {
                RepoItem(
                    name = it.name,
                    description = it.description,
                    starsCount = it.stargazersCount,
                    forkCount = it.forksCount
                )
            }
        )
    }
}
