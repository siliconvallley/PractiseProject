package com.xjsd.practiseproject.room.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * 用户和对应计划数据.
 *
 * 一对多的关系
 */
data class UserPlan(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val plans: List<Plan>
)
