package com.xjsd.practiseproject.room.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * 用户和对应工作数据.
 *
 * 一对多的关系
 */
class UserWorkList(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val workList: MutableList<Work>
) {
    override fun toString(): String {
        return "UserWorkList(user=$user, workList=$workList)"
    }
}