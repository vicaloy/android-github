package com.example.github.infra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.github.infra.database.entity.RepoEntity
import com.example.github.infra.database.entity.RepoWithOwner

@Dao
interface RepoDAO {
    @Transaction
    @Query(
        """
        SELECT * FROM repos 
        INNER JOIN owners ON repos.ownerId = owners.id
        WHERE owners.login = :login
    """
    )
    suspend fun getAllByOwner(login: String): List<RepoWithOwner>

    @Transaction
    @Query(
        """
        SELECT * FROM repos 
        INNER JOIN owners ON repos.ownerId = owners.id
        WHERE repos.name = :repo AND owners.login = :login
    """
    )
    suspend fun find(login: String, repo: String): RepoWithOwner

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepoEntity>)
}