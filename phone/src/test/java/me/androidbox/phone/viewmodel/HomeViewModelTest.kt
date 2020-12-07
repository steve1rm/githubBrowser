package me.androidbox.phone.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import me.androidbox.data.GithubApi
import me.androidbox.data.model.RepoApiModel
import me.androidbox.data.model.UserApiModel
import me.androidbox.domain.repository.AppRepositoryImp
import me.androidbox.phone.model.RepoItem
import me.androidbox.phone.viewstate.HomeViewState
import org.junit.Before


import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewStateValues: MutableList<HomeViewState>

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val appRepository = AppRepositoryImp(FakeGithubApi())
        viewStateValues = mutableListOf()
        viewModel = HomeViewModel(appRepository)
        viewModel.viewStateUpdates.observeForever { homeViewState ->
            viewStateValues.add(homeViewState)
        }
    }

    @Test
    fun `loaded state contains repo models`() {
        assertThat(viewStateValues.count()).isEqualTo(1)
        val expected = HomeViewState.HomeViewStateLoaded(
            repositoryList = listOf(
                RepoItem(
                    name = repoApiModel[0].name,
                    description = repoApiModel[0].description,
                    starsCount = repoApiModel[0].stargazersCount,
                    forkCount =  repoApiModel[0].forksCount
                )
            )
        )

        assertThat(viewStateValues[0]).isEqualTo(expected)
    }
}

class FakeGithubApi : GithubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return repoApiModel
    }
}

val repoApiModel by lazy {
    listOf(
        RepoApiModel(
            id = 1L,
            name = "mock repo",
            description = "Mock repo description",
            stargazersCount = 1,
            forksCount = 1,
            contributorsUrl = "",
            createdDate = "1/1/2020",
            updateDate = "1/1/2020",
            owner = UserApiModel(id = 1L, login = "dagger")
        )
    )
}