package com.example.github.infra.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.github.domain.model.Owner
import com.example.github.domain.model.Repo
import kotlin.math.log

@Entity(tableName = "owners")
data class OwnerEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "login") val login: String,
) {
    companion object {
        fun fromModel(owner: Owner) = OwnerEntity(
            id = owner.id, login = owner.login
        )
    }

    fun toModel() = Owner(
        id = id, login = login
    )
}