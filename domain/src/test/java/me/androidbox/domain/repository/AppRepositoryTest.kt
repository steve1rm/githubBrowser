package me.androidbox.domain.repository

import com.google.common.truth.Truth.assertThat
import me.androidbox.data.GithubApi
import me.androidbox.data.model.RepoApiModel
import me.androidbox.data.model.UserApiModel
import org.junit.Before
import org.junit.Test

class AppRepositoryTest {

    lateinit var appRepository: AppRepository

    lateinit var fakeGithubApi: FakeGithubApi

    @Before
    fun setUp() {
        fakeGithubApi = FakeGithubApi()
        appRepository = AppRepository(fakeGithubApi)
    }

    @Test
    fun `should return top repositories`() {
        // Arrange
        val topRepository = appRepository.getTopRepos()

        // Act & Assert
        assertThat(topRepository.size).isEqualTo(1)
        assertThat(topRepository).isEqualTo(repoApiModel)
    }
}

class FakeGithubApi(): GithubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return repoApiModel
    }
}

val repoApiModel by lazy {
    listOf(RepoApiModel(
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
