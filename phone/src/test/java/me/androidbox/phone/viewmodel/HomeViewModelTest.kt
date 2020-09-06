package me.androidbox.phone.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import me.androidbox.data.GithubApi
import me.androidbox.data.model.RepoApiModel
import me.androidbox.data.model.UserApiModel
import me.androidbox.domain.repository.AppRepository
import me.androidbox.phone.model.RepoItem
import me.androidbox.phone.viewstate.HomeViewState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appRepository: AppRepository
    private val fakeGithubApi: FakeGithubApi = FakeGithubApi()
    private lateinit var homeViewModel: HomeViewModel
    private val repositoryList = mutableListOf<HomeViewState>()

    @Before
    fun setUp() {
        appRepository = AppRepository(fakeGithubApi)
        homeViewModel = HomeViewModel(appRepository)

        homeViewModel.viewStateUpdates.observeForever {
            repositoryList.add(it)
        }
    }

    @Test
    fun `should return the top repositories`() {
        // Act
        val result = homeViewModel.viewStateUpdates.value

        // Assert
        assertThat(repositoryList.count()).isEqualTo(1)
        val expectedState = HomeViewState.HomeViewStateLoaded(
            repositoryList = listOf(RepoItem(
                name = "mock repo",
                description = "Mock repo description",
                starsCount = 1,
                forkCount = 1
            ))
        )
        assertThat(homeViewModel.viewStateUpdates.value).isEqualTo(expectedState)
    }
}

class FakeGithubApi : GithubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(RepoApiModel(
            id = 1L,
            name = "mock repo",
            description = "Mock repo description",
            stargazersCount = 1,
            forksCount = 1,
            contributorsUrl = "",
            createdDate = "1/1/2020",
            updateDate = "1/1/2020",
            owner = UserApiModel(id = 1L, login = "dagger")
        ))
    }
}

