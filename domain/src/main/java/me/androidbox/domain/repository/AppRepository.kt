package me.androidbox.domain.repository

import me.androidbox.data.GithubApi
import me.androidbox.data.model.RepoApiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val githubApi: GithubApi) {

    fun getTopRepos(): List<RepoApiModel> {
        return githubApi.getTopRepositories()
    }
}
