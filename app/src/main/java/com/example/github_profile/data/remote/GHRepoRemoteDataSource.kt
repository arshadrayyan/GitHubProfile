package com.example.github_profile.data.remote

import com.example.github_profile.data.model.RepoModel

// The remote data source is responsible for fetching data from the network (via Retrofit).

class GHRepoRemoteDataSource(private val networkService: NetworkService) {

    suspend fun fetchRepositories(username: String): List<RepoModel> {
        return networkService.fetchRepositories(username)
    }
}
