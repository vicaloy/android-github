package com.example.github.infra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.github.infra.database.dao.OwnerDAO
import com.example.github.infra.database.dao.RepoDAO
import com.example.github.infra.database.entity.OwnerEntity
import com.example.github.infra.database.entity.RepoEntity

@Database(entities = [RepoEntity::class, OwnerEntity::class], version = 1)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun repoDAO(): RepoDAO
    abstract fun ownerDAO(): OwnerDAO
}