package me.androidbox.data

import me.androidbox.data.model.RepoApiModel
import me.androidbox.data.model.UserApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MockGithubApiFactory @Inject constructor() : GithubApi {
    override fun getTopRepositories(): List<RepoApiModel> {
        return listOf(
            RepoApiModel(
                id = 1L,
                name = "mock repo",
                description = "Mock repo description",
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/1/2020",
                updateDate = "1/1/2020",
                owner = UserApiModel(id = 1L, login = "dagger")),

            RepoApiModel(
                id = 1L,
                name = "mock repo",
                description = "Mock repo description",
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/1/2020",
                updateDate = "1/1/2020",
                owner = UserApiModel(id = 1L, login = "dagger")),

            RepoApiModel(
                id = 1L,
                name = "mock repo",
                description = "Mock repo description",
                stargazersCount = 1,
                forksCount = 1,
                contributorsUrl = "",
                createdDate = "1/1/2020",
                updateDate = "1/1/2020",
                owner = UserApiModel(id = 1L, login = "dagger")))
    }
}
