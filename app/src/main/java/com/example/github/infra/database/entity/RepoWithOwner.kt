package com.example.github.infra.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.github.domain.model.Repo

data class RepoWithOwner(
    @Embedded val repo: RepoEntity,

    @Relation(
        parentColumn = "ownerId",
        entityColumn = "id"
    )
    val owner: OwnerEntity
){
    fun toModel() = Repo(
        id = repo.id,
        name = repo.name,
        owner = owner.toModel()
    )
}