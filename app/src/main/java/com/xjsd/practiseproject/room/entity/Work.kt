package com.xjsd.practiseproject.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
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
