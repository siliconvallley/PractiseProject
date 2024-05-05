package com.xjsd.practiseproject.room.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * 计划表对应的用户列表.
 */
data class PlanWithUsers(
    @Embedded val plan: Plan,
    @Relation(
        parentColumn = "planId",
        entityColumn = "id",
        associateBy = Junction(UserPlanCrossRef::class)
    )
    val users: MutableList<User>
)
