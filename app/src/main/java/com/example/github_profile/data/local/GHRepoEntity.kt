package com.example.github_profile.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room DataBase Entity

@Entity(tableName = "gh_repos")
data class GHRepoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val repoURL: String
)
