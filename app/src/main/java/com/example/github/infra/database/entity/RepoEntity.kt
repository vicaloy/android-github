package com.example.github.infra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.github.domain.model.Repo

@Entity(
    tableName = "repos", foreignKeys = [ForeignKey(
        entity = OwnerEntity::class,
        parentColumns = ["id"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class RepoEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "ownerId") val ownerId: Long
) {
    companion object {
        fun fromModel(repo: Repo) = RepoEntity(
            id = repo.id, name = repo.name, ownerId = repo.owner.id
        )
    }
}