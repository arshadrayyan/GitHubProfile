package com.example.github_profile.data

import com.example.github_profile.data.local.GHRepoEntity
import com.example.github_profile.data.local.GHRepoLocalDataSource
import com.example.github_profile.data.model.GHRepo
import com.example.github_profile.data.remote.GHRepoRemoteDataSource

// create the repository to manage both the local and remote data sources.

class GHRepoRepository(
    private val localDataSource: GHRepoLocalDataSource,
    private val remoteDataSource: GHRepoRemoteDataSource
) {

    suspend fun getRepositories(username: String): List<GHRepo> {
        // First, try fetching from the local cache
        val cachedRepos = localDataSource.getAllRepos()
        if (cachedRepos.isNotEmpty()) {
            return cachedRepos.map {
                GHRepo(
                    id = it.id.toLong(),
                    name = it.name,
                    repoURL = it.repoURL
                )
            }
        }

        // If no data is found locally, fetch from remote API
        val remoteRepos = remoteDataSource.fetchRepositories(username)

        // Cache the fetched data
        localDataSource.insertRepos(remoteRepos.map {
            GHRepoEntity(
                id = it.id.toInt(), // You must ensure the value of 'id' is within Int range
                name = it.name,
                repoURL = it.repoURL ?: ""
            )
        })

        // Convert and return the proper GHRepo list
        return remoteRepos.map {
            GHRepo(
                id = it.id,
                name = it.name,
                repoURL = it.repoURL ?: ""
            )
        }
    }

    suspend fun searchRepos(query: String): List<GHRepo> {
        val cachedRepos = localDataSource.searchRepos(query)
        return cachedRepos.map {
            GHRepo(
                id = it.id.toLong(),
                name = it.name,
                repoURL = it.repoURL
            )
        }
    }
}