package me.androidbox.domain.repository

import me.androidbox.data.GithubApi
import me.androidbox.data.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImp @Inject constructor(private val githubApi: GithubApi) : AppRepository {

    override fun getTopRepos(): List<RepoApiModel> {
        return githubApi.getTopRepositories()
    }
}
