package com.example.github_profile.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// provides methods for accessing the Room database.

@Dao
interface GHRepoDao {

    @Insert
    suspend fun insertRepos(repos: List<GHRepoEntity>)

    @Query("SELECT * FROM gh_repos")
    suspend fun getAllRepos(): List<GHRepoEntity>

    @Query("SELECT * FROM gh_repos WHERE name LIKE :query OR id = :query")
    suspend fun searchRepos(query: String): List<GHRepoEntity>
}
