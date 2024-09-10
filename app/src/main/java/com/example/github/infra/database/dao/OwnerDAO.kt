package com.example.github.infra.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.github.infra.database.entity.OwnerEntity

@Dao
interface OwnerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(owners: List<OwnerEntity>)
}