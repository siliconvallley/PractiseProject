package com.xjsd.practiseproject.room.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * 用户和对应工作数据.
 *
 * 一对一的关系
 */
data class UserWork(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val work: Work
) {
    override fun toString(): String {
        return "UserWork(user=$user, work=$work)"
    }
}
