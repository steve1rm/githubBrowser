package me.androidbox.phone.viewstate

import me.androidbox.phone.model.RepoItem

sealed class HomeViewState {
    object HomeViewStateLoading : HomeViewState()
    data class HomeViewStateLoaded(val repositoryList: List<RepoItem>) : HomeViewState()
    data class HomeViewStateError(val message: String) : HomeViewState()
}
