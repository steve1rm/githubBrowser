package me.androidbox.data.di

import dagger.Binds
import dagger.Module
import me.androidbox.data.GithubApi
import me.androidbox.data.MockGithubApiFactory

@Module
interface GithubApiModule {

    @Binds
    fun bindGithubApi(mockGithubApiFactory: MockGithubApiFactory): GithubApi
}
