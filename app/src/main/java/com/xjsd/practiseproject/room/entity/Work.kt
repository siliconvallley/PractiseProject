package com.xjsd.practiseproject.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Work(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val introduce: String,
    /**
     * 外键，对应用户表的id.
     */
    val userId: Long
) {
    override fun toString(): String {
        return "Work(id=$id, introduce='$introduce', userId=$userId)"
    }
}
