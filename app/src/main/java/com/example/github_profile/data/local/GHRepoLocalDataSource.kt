package com.example.github_profile.data.local

// The local data source interacts with Room to fetch cached data.

class GHRepoLocalDataSource(private val ghRepoDao: GHRepoDao) {

    suspend fun getAllRepos(): List<GHRepoEntity> {
        return ghRepoDao.getAllRepos()
    }

    suspend fun searchRepos(query: String): List<GHRepoEntity> {
        return ghRepoDao.searchRepos(query)
    }

    suspend fun insertRepos(repos: List<GHRepoEntity>) {
        ghRepoDao.insertRepos(repos)
    }
}
