package me.androidbox.data

import me.androidbox.data.model.RepoApiModel

interface GithubApi {
    fun getTopRepositories(): List<RepoApiModel>
}
