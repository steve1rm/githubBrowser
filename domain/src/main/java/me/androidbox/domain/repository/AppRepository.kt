package me.androidbox.domain.repository

import me.androidbox.data.model.RepoApiModel

interface AppRepository {
    fun getTopRepos(): List<RepoApiModel>
}
