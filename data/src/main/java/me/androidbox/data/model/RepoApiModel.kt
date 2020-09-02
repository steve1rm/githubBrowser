package me.androidbox.data.model

data class RepoApiModel(
    val id: Long,
    val name: String,
    val description: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val contributorsUrl: String,
    val createdDate: String,
    val updateDate: String,
    val owner: UserApiModel)
