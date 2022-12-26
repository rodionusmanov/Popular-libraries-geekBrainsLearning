package com.example.popularlibraries.core.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "repositories", foreignKeys = [
    ForeignKey(
        entity = UserDBObject::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )
])
data class RepoDBObjectOld(

    @PrimaryKey
    val id: Long,
    val forks: Int,
    val name: String,
    val userId: Long
)